package com.concordia.controller;

import com.concordia.common.util.JwtTokenUtil;
import com.concordia.pojo.User;
import com.concordia.rpcDomain.common.RespResult;
import com.concordia.rpcDomain.common.ResultCode;
import com.concordia.rpcDomain.request.LoginRequest;
import com.concordia.rpcDomain.request.RegisterRequest;
import com.concordia.service.UserService;
import com.concordia.token.JwtIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/get_captcha"
            , method = RequestMethod.POST
            , produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @JwtIgnore
    public RespResult getCaptcha(@RequestBody RegisterRequest registerRequest) {
        return userService.beforeRegister(registerRequest);
    }

    @PostMapping(value = "/register", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    @JwtIgnore
    public RespResult register(@RequestBody RegisterRequest registerRequest) {
        try {
            if (!userService.checkCaptcha(registerRequest)) {
                return new RespResult(ResultCode.WRONG_CAPTCHA);
            }
        } catch (NullPointerException npt) {
            return new RespResult(ResultCode.REGISTER_RECORD_IS_EMPTY);
        }

        return userService.registerUser(registerRequest);

    }

    @PostMapping(value = "/login", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @JwtIgnore
    public RespResult login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        User user = userService.getUserByUsername(loginRequest.getUsername());
        if (!userService.checkVerified(user)) {
            return new RespResult(ResultCode.USER_UN_VERIFIED);
        }
        if (!userService.checkPassword(user, loginRequest)) {
            return new RespResult(ResultCode.WRONG_PASSWORD);
        }

        String token = JwtTokenUtil.createJWT(user.getUsername(), user.getId());
        logger.info("user: " + user.getUsername() + "login successfully");
        logger.info("Token: " + token);
        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        return new RespResult(ResultCode.SUCCESS, map);
    }

}

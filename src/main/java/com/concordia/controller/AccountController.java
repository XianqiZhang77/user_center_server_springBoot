package com.concordia.controller;

import com.concordia.common.util.JwtTokenUtil;
import com.concordia.rpcDomain.common.RespResult;
import com.concordia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/center", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult center(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue) {
        return userService.getAccountCenterInfo(JwtTokenUtil.getUserIdByAuthorHead(headerValue));
    }
}

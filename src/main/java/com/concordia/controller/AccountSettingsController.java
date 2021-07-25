package com.concordia.controller;

import com.concordia.common.util.JwtTokenUtil;
import com.concordia.rpcDomain.common.RespResult;
import com.concordia.rpcDomain.request.UserPreferenceReq;
import com.concordia.service.UserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account/settings")
public class AccountSettingsController {

    @Autowired
    private UserPreferenceService userPreferenceService;

    @PostMapping(value = "/notice/show", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult showNotice(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return userPreferenceService.getNoticeByUserId(userId);
    }

    @RequestMapping("/notice/update")
    @ResponseBody
    public RespResult updateNotice(@RequestBody UserPreferenceReq userPreferenceReq,
                                   @RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue) {

        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return userPreferenceService.updateNoticeByUserId(userId, userPreferenceReq);
    }

}

package com.concordia.controller;

import com.concordia.common.util.JwtTokenUtil;
import com.concordia.rpcDomain.common.RespResult;
import com.concordia.rpcDomain.request.UserPreferenceReq;
import com.concordia.rpcDomain.request.UserProfileRequest;
import com.concordia.service.UserPreferenceService;
import com.concordia.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account/settings")
public class AccountSettingsController {

    @Autowired
    private UserPreferenceService userPreferenceService;

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping(value = "/notice/show", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult showNotice(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return userPreferenceService.getNoticeByUserId(userId);
    }

    @PostMapping(value = "/notice/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult updateNotice(@RequestBody UserPreferenceReq userPreferenceReq,
                                   @RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue) {

        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return userPreferenceService.updateNoticeByUserId(userId, userPreferenceReq);
    }

    @PostMapping(value = "/profile/show", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult showProfile(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return userProfileService.getUserProfileById(userId);
    }

    @PostMapping(value = "/profile/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult updateProfile(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue
            , @RequestBody UserProfileRequest userProfileRequest) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return userProfileService.updateUserProfileById(userId, userProfileRequest);
    }


}

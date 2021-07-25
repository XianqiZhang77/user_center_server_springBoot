package com.concordia.service;

import com.concordia.pojo.UserProfile;
import com.concordia.rpcDomain.common.RespResult;
import com.concordia.rpcDomain.request.UserProfileRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserProfileService extends BaseService<UserProfile, String> {
    RespResult getUserProfileById(String userId);

    RespResult updateUserProfileById(String userId, UserProfileRequest userProfileRequest);
}

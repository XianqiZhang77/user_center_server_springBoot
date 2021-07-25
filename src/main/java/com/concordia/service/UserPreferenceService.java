package com.concordia.service;

import com.concordia.pojo.UserPreference;
import com.concordia.rpcDomain.common.RespResult;
import com.concordia.rpcDomain.request.UserPreferenceReq;
import org.springframework.stereotype.Service;

@Service
public interface UserPreferenceService extends BaseService<UserPreference, String>{
    RespResult getNoticeByUserId(String userId);

    RespResult updateNoticeByUserId(String userId, UserPreferenceReq userPreferenceReq);

}

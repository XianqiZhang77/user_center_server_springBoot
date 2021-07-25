package com.concordia.service.impl;

import com.concordia.dao.UserPreferenceDao;
import com.concordia.pojo.User;
import com.concordia.pojo.UserPreference;
import com.concordia.rpcDomain.common.RespResult;
import com.concordia.rpcDomain.common.ResultCode;
import com.concordia.rpcDomain.request.UserPreferenceReq;
import com.concordia.rpcDomain.response.UserPreferenceResp;
import com.concordia.service.UserPreferenceService;
import com.concordia.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPreferenceServiceImpl extends BaseServiceImpl<UserPreference, String>
        implements UserPreferenceService {

    @Autowired
    private UserPreferenceDao userPreferenceDao;

    @Autowired
    private UserService userService;

    @Override
    protected JpaRepository getJpaRepository() {
        return userPreferenceDao;
    }

    @Override
    public RespResult getNoticeByUserId(String userId) {
        if (!StringUtils.isNotBlank(userId)) {
            return new RespResult(ResultCode.PARAM_IS_BLANK);
        }

        Optional<User> userOptional = userService.findById(userId);

        if (!userOptional.isPresent()) {
            return new RespResult(ResultCode.USER_NOT_EXIST);
        }

        UserPreference userPreference = userPreferenceDao.findByUserId(userId);

        UserPreferenceResp userPreferenceResp = new UserPreferenceResp();

        BeanUtils.copyProperties(userPreference, userPreferenceResp);

        return new RespResult(ResultCode.SUCCESS, userPreferenceResp);

    }

    @Override
    public RespResult updateNoticeByUserId(String userId, UserPreferenceReq userPreferenceReq) {
        UserPreference userPreference = new UserPreference();
        BeanUtils.copyProperties(userPreferenceReq, userPreference);
        userPreference.setUserId(userId);
        userPreferenceDao.save(userPreference);
        return new RespResult(ResultCode.SUCCESS);
    }
}

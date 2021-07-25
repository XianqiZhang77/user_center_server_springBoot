package com.concordia.service.impl;

import com.concordia.dao.UserProfileDao;
import com.concordia.pojo.Address;
import com.concordia.pojo.User;
import com.concordia.pojo.UserProfile;
import com.concordia.rpcDomain.common.RespResult;
import com.concordia.rpcDomain.common.ResultCode;
import com.concordia.rpcDomain.request.UserProfileRequest;
import com.concordia.rpcDomain.response.UserProfileResponse;
import com.concordia.service.AddressService;
import com.concordia.service.UserProfileService;
import com.concordia.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl extends BaseServiceImpl<UserProfile, String>
        implements UserProfileService {

    @Autowired
    private UserProfileDao userProfileDao;

    @Autowired
    private UserService userService;
    
    @Autowired
    private AddressService addressService;

    @Override
    protected JpaRepository getJpaRepository() {
        return userProfileDao;
    }

    @Override
    public RespResult getUserProfileById(String userId) {
        Optional<User> userOptional = userService.findById(userId);
        if (!userOptional.isPresent()) {
            return new RespResult(ResultCode.USER_NOT_EXIST);
        }
        User user = userOptional.get();
        Address address = addressService.findById(user.getId()).get();
        UserProfile userProfile = userProfileDao.findById(user.getId()).get();
        UserProfileResponse userProfileResponse = new UserProfileResponse();
        BeanUtils.copyProperties(user, userProfileResponse);
        BeanUtils.copyProperties(address, userProfileResponse);
        BeanUtils.copyProperties(userProfile, userProfileResponse);

        return new RespResult(ResultCode.SUCCESS, userProfileResponse);
    }

    @Override
    public RespResult updateUserProfileById(String userId, UserProfileRequest userProfileRequest) {
        Optional<User> userOptional = userService.findById(userId);
        if (!userOptional.isPresent()) {
            return new RespResult(ResultCode.USER_NOT_EXIST);
        }
        User user = userOptional.get();
        Address address = addressService.findById(user.getId()).get();
        UserProfile userProfile = userProfileDao.findById(user.getId()).get();

        BeanUtils.copyProperties(userProfileRequest, address);
        BeanUtils.copyProperties(userProfileRequest, userProfile);
        BeanUtils.copyProperties(userProfileRequest, user);
        userService.save(user);
        addressService.save(address);
        userProfileDao.save(userProfile);
        return new RespResult(ResultCode.SUCCESS);
    }
}

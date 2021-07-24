package com.concordia.service.impl;

import com.concordia.dao.UserProfileDao;
import com.concordia.pojo.UserProfile;
import com.concordia.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl extends BaseServiceImpl<UserProfile, String>
        implements UserProfileService {

    @Autowired
    private UserProfileDao userProfileDao;

    @Override
    protected JpaRepository getJpaRepository() {
        return userProfileDao;
    }
}

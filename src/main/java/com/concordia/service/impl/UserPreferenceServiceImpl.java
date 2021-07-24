package com.concordia.service.impl;

import com.concordia.dao.UserPreferenceDao;
import com.concordia.pojo.UserPreference;
import com.concordia.service.UserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserPreferenceServiceImpl extends BaseServiceImpl<UserPreference, String>
        implements UserPreferenceService {

    @Autowired
    private UserPreferenceDao userPreferenceDao;


    @Override
    protected JpaRepository getJpaRepository() {
        return userPreferenceDao;
    }

}

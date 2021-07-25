package com.concordia.service.impl;

import com.concordia.dao.UserTagDao;
import com.concordia.pojo.UserTag;
import com.concordia.service.UserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTagServiceImpl extends BaseServiceImpl<UserTag, String>
        implements UserTagService {

    @Autowired
    private UserTagDao userTagDao;

    @Override
    protected JpaRepository getJpaRepository() {
        return userTagDao;
    }

    @Override
    public List<String> getUserTagList(String userId) {
        return userTagDao.getUserTagList(userId);
    }
}

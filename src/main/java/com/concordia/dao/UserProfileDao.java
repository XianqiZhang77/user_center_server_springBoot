package com.concordia.dao;

import com.concordia.pojo.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserProfileDao extends JpaRepository<UserProfile, String> {

}

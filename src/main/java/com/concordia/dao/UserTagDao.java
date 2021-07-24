package com.concordia.dao;

import com.concordia.pojo.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTagDao extends JpaRepository<UserTag, String> {

}

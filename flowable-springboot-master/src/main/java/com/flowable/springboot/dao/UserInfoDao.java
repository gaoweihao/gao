package com.flowable.springboot.dao;

import com.flowable.springboot.bean.UserInfoEntity;

import java.util.List;

public interface UserInfoDao {//extends JpaRepository<Person, Long>

    UserInfoEntity findByUsername(String username);

    int save(UserInfoEntity user);

    List<UserInfoEntity> findList();

    UserInfoEntity selectByUserId(long userId);
}

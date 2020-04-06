package com.flowable.springboot.service;

import com.flowable.springboot.bean.UserInfoEntity;

public interface UserInfoService{

    int save(UserInfoEntity user);

    UserInfoEntity selectByUserId(long userId);
}

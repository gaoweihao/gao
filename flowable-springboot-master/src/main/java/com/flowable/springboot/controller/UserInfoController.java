package com.flowable.springboot.controller;

import com.flowable.springboot.bean.UserInfoEntity;
import com.flowable.springboot.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 保存用户信息
     *
     * @param user
     */
    @RequestMapping("/user")
    public void save(UserInfoEntity user) {
        userInfoService.save(user);
    }

    /**
     * 通过用户主键查询用户
     *
     * @param userId
     */
    @RequestMapping("/select-by-userId/{userId}")
    public UserInfoEntity selectByUserId(@PathVariable long userId) {
        UserInfoEntity user = userInfoService.selectByUserId(userId);
        System.out.println(user.getUserName());
       return userInfoService.selectByUserId(userId);
    }
}

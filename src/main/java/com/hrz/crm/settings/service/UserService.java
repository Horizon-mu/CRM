package com.hrz.crm.settings.service;

import com.hrz.crm.settings.entity.User;

import java.util.List;


public interface UserService{
    //登录
    User login(String loginAct,String loginPwd,String ip) throws Exception ;
    //获取用户列表
    List<User> getUserList();
}

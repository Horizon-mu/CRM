package com.hrz.crm.settings.dao;

import com.hrz.crm.settings.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserDao {

    User selectUser(@Param("account") String loginAct,@Param("password") String loginPwd);
    User queryUserId(String id);
    //获取用户列表
    List<User> getUserList();


}

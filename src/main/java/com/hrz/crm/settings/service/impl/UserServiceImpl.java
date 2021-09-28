package com.hrz.crm.settings.service.impl;

import com.hrz.crm.settings.dao.UserDao;
import com.hrz.crm.settings.entity.User;
import com.hrz.crm.settings.service.UserService;
import com.hrz.crm.settings.web.Exception.*;
import com.hrz.crm.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String loginAct, String loginPwd, String ip) throws MyException {
        User user =  userDao.selectUser(loginAct,loginPwd);
        if (user == null){
            throw new UserLoginInforException();
        }
        /*
            验证过期时间
            验证账户锁定状态
            验证IP是否合法
         */
        String expireTime = user.getExpireTime();
        String lockState = user.getLockState();
        String allowIps = user.getAllowIps();
        String time = DateTimeUtil.getSysTime();
        if(time.compareTo(expireTime) > 0){
            throw new UserExpireTimeException();
        }
        if ("0".equals(lockState)){
            throw new UserLockStateException();
        }
        if (!allowIps.contains(ip)){
            throw new UserIpException();
        }
        return user;
    }

    //获取用户列表
    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }


}


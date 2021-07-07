package com.hrz.crm.settings.service;

import com.hrz.crm.settings.entity.User;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/05/10/17:09
 * @Description:
 */
public interface UserService{
    User login(String loginAct,String loginPwd,String ip) throws Exception ;

}

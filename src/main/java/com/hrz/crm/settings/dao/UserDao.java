package com.hrz.crm.settings.dao;

import com.hrz.crm.settings.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/05/10/16:26
 * @Description:
 */
public interface UserDao {

    User selectUser(@Param("account") String loginAct,@Param("password") String loginPwd);
    User queryUserId(String id);

}

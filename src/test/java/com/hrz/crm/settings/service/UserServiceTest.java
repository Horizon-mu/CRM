package com.hrz.crm.settings.service;

import com.hrz.crm.settings.entity.User;
import com.hrz.crm.settings.service.impl.UserServiceImpl;
import com.hrz.crm.settings.web.Exception.*;
import com.hrz.crm.utils.MD5Util;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/06/30/8:40
 * @Description:
 */

public class UserServiceTest {


    private UserService userService;
    @Before
    public void setUp() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/conf/applicationContext.xml");
        String[] s = applicationContext.getBeanDefinitionNames();
        for(String string : s){
            System.out.println(string);
        }
        userService = (UserService) applicationContext.getBean("UserService");
        System.out.println(userService);
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     *
     */
    @Test
    public void login(){
        User user = new User();
        List<Object> list = null;
        //判定覆盖，用例1
        user.setLoginAct("zs");
        user.setLoginPwd("123");
        user.setAllowIps("127.0.0.1");
        list = login(user);
        Assert.assertNotNull(list.get(1));
        Assert.assertEquals("",list.get(0));

        //判定覆盖，用例2
        user.setLoginAct("wu");
        user.setLoginPwd("123");
        user.setAllowIps("127.0.0.2");
        list = login(user);
        //Assert.assertEquals("UserLoginInforException",result2);
        Assert.assertNull(list.get(1));
        Assert.assertEquals("UserLoginInforException",list.get(0));

        //用例1
        user.setLoginAct("zs");
        user.setLoginPwd("123");
        user.setAllowIps("127.0.0.1");
        list = login(user);
        Assert.assertEquals("",list.get(0));
        Assert.assertNotNull(list.get(1));

        //用例2

        //user== null true
        user.setLoginAct("zs");
        user.setLoginPwd("124");
        //IP false
        user.setAllowIps("127.0.0.1");
        //过期时间 false
        //锁定状态 false
        list = login(user);
        Assert.assertEquals("UserLoginInforException",list.get(0));
        Assert.assertNull(list.get(1));

        //用例3
        //过期时间 false
        //锁定状态 false
        //IP false
        //user== null false
        user.setLoginAct("zs");
        user.setLoginPwd("123");
        user.setAllowIps("127.0.0.2");
        list = login(user);
        Assert.assertEquals("UserIpException",list.get(0));
        Assert.assertNull(list.get(1));

        //用例4
        //账户锁定
        user.setLoginAct("ww");
        user.setLoginPwd("123");
        user.setAllowIps("127.0.0.1");
        list = login(user);
        Assert.assertEquals("UserLockStateException",list.get(0));
        Assert.assertNull(list.get(1));

        //用例5
        //账户过期
        user.setLoginAct("zl");
        user.setLoginPwd("123");
        user.setAllowIps("127.0.0.1");
        list = login(user);
        Assert.assertEquals("UserExpireTimeException",list.get(0));
        Assert.assertNull(list.get(1));

    }

    /**
     *
     * @param user
     * @return list
     *  list.get(0) 得到result，表示异常信息
     *  list.get(1) 得到user，表示查询到的用户信息
     */
    public List<Object> login(User user){
        String result = "";
        String loginAct = user.getLoginAct();
        String loginPwd = user.getLoginPwd();
        String ip = user.getAllowIps();
        user = null;
        try {
            user = userService.login(loginAct, MD5Util.getMD5(loginPwd), ip);
        } catch (UserLoginInforException e) {
            result = "UserLoginInforException";
        } catch (UserIpException e){
            result="UserIpException";
        } catch (UserExpireTimeException e){
            result = "UserExpireTimeException";
        } catch (UserLockStateException e){
            result = "UserLockStateException";
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            List<Object> list = new ArrayList<>();
            list.add(0,result);
            list.add(1,user);
            return list;
            }
        }

}
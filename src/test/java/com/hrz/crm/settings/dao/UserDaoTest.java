package com.hrz.crm.settings.dao;

import com.hrz.crm.settings.entity.User;
import com.hrz.crm.utils.MD5Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/06/30/9:46
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/conf/applicationContext.xml","classpath*:/conf/dispatcherServlet.xml","classpath*:/conf/mybatis.xml"})
@Rollback
public class UserDaoTest {

    @Autowired
    private UserDao userDao;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void selectUser() {

        //测试用例1
        User user1 = userDao.selectUser("zs", MD5Util.getMD5("123"));
        Assertions.assertNotNull(user1);
        //测试用例2
        User user2 = userDao.selectUser("ww", MD5Util.getMD5("123"));
        Assertions.assertNotNull(user2);
    }
}
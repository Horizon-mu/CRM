package com.hrz.crm.settings.web.controller;

import com.hrz.crm.settings.entity.User;
import com.hrz.crm.settings.service.UserService;
import com.hrz.crm.settings.service.impl.UserServiceImpl;
import com.hrz.crm.settings.web.Exception.*;
import com.hrz.crm.utils.DateTimeUtil;
import com.hrz.crm.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl service;
    @RequestMapping(value="/login.do",method = RequestMethod.POST)
    @ResponseBody
    public Map doLogin(HttpServletRequest request, HttpServletResponse response) throws MyException {


        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");
        loginPwd = MD5Util.getMD5(loginPwd);
        //
        //System.out.println(loginPwd);
        String ip = request.getRemoteAddr();
        System.out.println("-------------ip:" + ip);
        User user = service.login(loginAct,loginPwd,ip);
        /*Cookie cookie = new Cookie("ID",user.getId());
        response.addCookie(cookie);*/
        request.getSession().setAttribute("user",user);
        Map map = new HashMap();
        map.put("success",true);
        return map;
    }

    @RequestMapping(value = "/getUserList.do")
    @ResponseBody
    public List<User> getUserList(){
        System.out.println("---------------->进入获取用户列表的方法");
        List<User> uList = new LinkedList<>();
        uList = service.getUserList();
        return uList;
    }

}

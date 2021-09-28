package com.hrz.crm.settings.web.handler;

import com.hrz.crm.settings.web.Exception.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = UserLoginInforException.class)
    public Map doUserExistException(){
        Map map = new HashMap();
        map.put("success",false);
        map.put("msg","用户不存在！！！请核对登录信息");
        return map;
    }
    @ResponseBody
    @ExceptionHandler(value = UserExpireTimeException.class)
    public Map doUserExpireTimeException(){
        Map map = new HashMap();
        map.put("success",false);
        map.put("msg","账户已过期，请联系管理员~~~");
        return map;
    }
    @ResponseBody
    @ExceptionHandler(value = UserLockStateException.class)
    public Map doUserLockStateException(){
        Map map = new HashMap();
        map.put("success",false);
        map.put("msg","账户已锁定，请联系管理员~~~");
        return map;
    }
    @ResponseBody
    @ExceptionHandler(value = UserIpException.class)
    public Map doUseIpException(){
        Map map = new HashMap();
        map.put("success",false);
        map.put("msg","非法IP，请联系管理员~~");
        return map;
    }

    @ExceptionHandler(value = NoLoginException.class)
    public String doNoLoginException(HttpServletRequest request, HttpServletResponse response){
        return "/login.jsp";
    }

}

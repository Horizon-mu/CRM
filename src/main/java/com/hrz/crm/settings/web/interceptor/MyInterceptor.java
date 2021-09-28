package com.hrz.crm.settings.web.interceptor;

import com.hrz.crm.settings.dao.UserDao;
import com.hrz.crm.settings.entity.User;
import com.hrz.crm.settings.web.Exception.NoLoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyInterceptor implements HandlerInterceptor {
    @Autowired
    private UserDao dao;
    /**
     * 判断用户是否是登录状态
     * 获取cooKies对象，解析用户的ID，如果用户ID不为空，且在数据库中有对应的用户记录，表示
     * 请求合法，否则请求不合法，拦截该请求，重定向到登录界面
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        User user = null;
        if (session != null){
            user = (User) session.getAttribute("user");
            if (user != null){
                return true;
            }else{
                response.sendRedirect("/myweb/index.jsp");
                return false;
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

package com.hrz.crm.settings.web.listener;

import com.hrz.crm.settings.entity.DicValue;
import com.hrz.crm.settings.service.DicService;
import com.hrz.crm.settings.service.impl.DicServiceImpl;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/05/25/10:17
 * @Description:
 */
public class SysInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("监听器~~~~~~~~");
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        /*String[] str = webApplicationContext.getBeanDefinitionNames();
        for(String s : str){
            System.out.println(s);
        }*/
        DicService dicService= (DicServiceImpl) webApplicationContext.getBean("dicServiceImpl");
        ServletContext application = sce.getServletContext();
        System.out.println(application);
        System.out.println(dicService);
        Map<String, List<DicValue>> map = dicService.getAll();
        Set<String> set = map.keySet();
        for(String s : set) {
            application.setAttribute(s, map.get(s));
        }
        System.out.println(application.getAttribute("appellation"));
        System.out.println(application.getAttribute("clueState"));
        System.out.println(application.getAttribute("source"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}

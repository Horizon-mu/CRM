package com.hrz.crm.workbench.web.controller;

import com.hrz.crm.settings.entity.User;
import com.hrz.crm.settings.service.impl.UserServiceImpl;
import com.hrz.crm.utils.DateTimeUtil;
import com.hrz.crm.utils.UUIDUtil;
import com.hrz.crm.vo.PaginationVo;
import com.hrz.crm.workbench.entity.Activity;
import com.hrz.crm.workbench.entity.ActivityRemark;
import com.hrz.crm.workbench.service.impl.ActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


//市场活动处理
@RequestMapping(value = "/activity")
@Controller
public class ActivityController {
    @Autowired
    private ActivityServiceImpl activityService;
    @Autowired
    private UserServiceImpl userService;


    @RequestMapping(value = "/getUserList.do")
    @ResponseBody
    public List<User> getUserList(){
        System.out.println("---------------->进入获取用户列表的方法");
        List<User> uList = userService.getUserList();
        return uList;
    }
    @RequestMapping(value = "/save.do")
    @ResponseBody
    public boolean doSave(Activity activity, HttpServletRequest request){
        System.out.println("---------------->进入保存市场活动的方法");
        String id = UUIDUtil.getUUID();
        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User)request.getSession().getAttribute("user")).getName();
        activity.setId(id);
        activity.setCreateTime(createTime);
        activity.setCreateBy(createBy);
        boolean result = activityService.save(activity);
        return result;
    }

    @RequestMapping(value = "/pageList.do")
    @ResponseBody
    public PaginationVo<Activity> doPageList(HttpServletRequest request){
        System.out.println("---------------->进入市场活动列表查询的方法");
        String pageNo = request.getParameter("pageNo");
        int no = Integer.valueOf(pageNo);
        String pageSize = request.getParameter("pageSize");
        int size  = Integer.valueOf(pageSize);
        int skipCount = (no - 1) * size;

        Map<String,Object> map = new HashMap<>();
        map.put("name",request.getParameter("name"));
        map.put("owner",request.getParameter("owner"));
        map.put("startDate",request.getParameter("startDate"));
        map.put("endDate",request.getParameter("endDate"));
        map.put("skipCount",skipCount);
        map.put("pageSize",size);

        return activityService.doPageList(map);
    }
    //删除市场活动---->数组接收
    @PostMapping(value = "/delete.do")
    @ResponseBody
    public boolean delete(String[] id){
        System.out.println("执行市场活动删除操作~~~~~~~~~~~~~~~~");
        return activityService.doDelete(id);
    }

    //提前渲染修改市场活动模态窗口
    @GetMapping(value = "preEditActivity.do")
    @ResponseBody
    public Map preEditActivity(String id){
        System.out.println("进入提前渲染市场活动模块~~~~~~~~~~~~~~~~~");
        Activity activity = activityService.selectActivityById(id);
        Map<String,Object> map = new HashMap<>();
        List<User> uList = userService.getUserList();
        map.put("activity",activity);
        map.put("uList",uList);
        return map;
    }
    //真正修改市场活动
    @PostMapping(value = "editActivity.do")
    @ResponseBody
    public boolean editActivity(Activity activity){
        System.out.println("进入真正修改市场活动模块~~~~~~~~~~~~~~");
        boolean flag = true;
        int n = activityService.doEdit(activity);
        if (1 != n){
            flag = false;
        }
        return flag;
    }

    //提前渲染
    @RequestMapping(value = "/detail.do")
    public String doActivityPreViewDetail(String id, HttpServletRequest request){
        Activity activity = activityService.selectActivityById(id);
        List<ActivityRemark> activityRemarks = activityService.selectActivityRemarkById(id);
        request.setAttribute("activity",activity);
        request.setAttribute("rList",activityRemarks);
        return "forward:/workbench/activity/detail.jsp";
    }

    //保存评论
    @RequestMapping(value = "/saveRemark.do")
    @ResponseBody
    public boolean saveRemark(String remark,String activityId,HttpServletRequest request){
        boolean flag = true;
        User user = (User) request.getSession().getAttribute("user");
        ActivityRemark activityRemark = new ActivityRemark();
        activityRemark.setId(UUIDUtil.getUUID());
        activityRemark.setCreateBy(user.getName());
        activityRemark.setCreateTime(DateTimeUtil.getSysTime());
        activityRemark.setNoteContent(remark);
        activityRemark.setActivityId(activityId);
        activityRemark.setEditFlag("0");
        flag = activityService.doSaveRemark(activityRemark);
        return flag;
    }
}

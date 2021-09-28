package com.hrz.crm.workbench.service;

import com.hrz.crm.settings.entity.User;
import com.hrz.crm.vo.PaginationVo;
import com.hrz.crm.workbench.entity.Activity;
import com.hrz.crm.workbench.entity.ActivityRemark;

import java.util.List;
import java.util.Map;


public interface ActivityService {
    //添加市场活动
    boolean save(Activity activity);
    //分页
    PaginationVo<Activity> doPageList(Map<String,Object> map);
    //删除市场活动
    boolean doDelete(String[] id);
    //按照id查询市场活动
    Activity selectActivityById(String id);
    //修改市场活动
    int doEdit(Activity activity);

    List<ActivityRemark> selectActivityRemarkById(String id);

    boolean doSaveRemark(ActivityRemark activityRemark);

    //按条件查询市场活动
    List<Activity> getActivityListByNameAndNotInClueId(Map<String, String> map);
    //在市场活动中添加备注

    //按照id获取关联的活动
    List<Activity> getActivityByClueId(String id);

}

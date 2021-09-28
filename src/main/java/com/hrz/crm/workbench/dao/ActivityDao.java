package com.hrz.crm.workbench.dao;



import com.hrz.crm.workbench.entity.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityDao {
    //保存新建的市场活动
    int save(Activity activity);
    //查询市场活动列表
    List<Activity> getDataListByCondition(Map<String, Object> map);
    //条件下的记录总条数
    int getTotalByCondition(Map<String, Object> map);
    //删除市场活动
    int doDelete(String[] id);
    //按照id查询市场活动
    Activity selectActivityById(String id);
    //修改市场活动
    int doEdit(Activity activity);

    //按条件查询市场活动
    List<Activity> getActivityListByNameAndNotInClueId(Map<String, String> map);
    //按照clueId获取关联的活动
    List<Activity> getActivityByClueId(String id);
}

package com.hrz.crm.workbench.service;

import com.hrz.crm.settings.entity.User;
import com.hrz.crm.vo.PaginationVo;
import com.hrz.crm.workbench.entity.Activity;
import com.hrz.crm.workbench.entity.ActivityRemark;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/05/11/17:24
 * @Description:
 */
public interface ActivityService {
    //查询user表，得到用户信息
    List<User> getUserList();
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
    //在市场活动中添加备注

}

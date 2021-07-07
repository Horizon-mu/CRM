package com.hrz.crm.workbench.dao;

import com.hrz.crm.workbench.entity.ActivityRemark;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/05/21/16:49
 * @Description:
 */
public interface ActivityRemarkDao {
    int doDelete(String[] id);

    int getCountByIds(String[] id);
    //备注查询
    List<ActivityRemark> selectActivityRemarkById(String id);

    //添加市场活动备注
    int doSaveRemark(ActivityRemark activityRemark);
}

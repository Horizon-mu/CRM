package com.hrz.crm.workbench.dao;


import com.hrz.crm.settings.entity.User;
import com.hrz.crm.vo.PaginationVo;
import com.hrz.crm.workbench.entity.Activity;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/05/11/16:10
 * @Description:
 */
public interface ActivityDao {
    //点击市场活动内的创建按钮，默认拥有者，查询userList信息
    List<User> getUserList();
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

}

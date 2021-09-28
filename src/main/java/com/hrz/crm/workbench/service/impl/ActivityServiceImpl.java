package com.hrz.crm.workbench.service.impl;

import com.hrz.crm.settings.entity.User;
import com.hrz.crm.vo.PaginationVo;
import com.hrz.crm.workbench.dao.ActivityDao;
import com.hrz.crm.workbench.dao.ActivityRemarkDao;
import com.hrz.crm.workbench.entity.Activity;
import com.hrz.crm.workbench.entity.ActivityRemark;
import com.hrz.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private ActivityRemarkDao activityRemarkDao;

    @Override
    public boolean save(Activity activity) {
        boolean flag = true;
        int i = activityDao.save(activity);
        if (i != 1){
            flag = false;
        }
        return flag;
    }

    @Override
    public PaginationVo<Activity> doPageList(Map<String,Object> map) {
        //得到记录总条数
        int total = activityDao.getTotalByCondition(map);
        //得到dataList
        List<Activity> list = activityDao.getDataListByCondition(map);
        //将结果封装到vo中
        PaginationVo<Activity> paginationVo = new PaginationVo<>();
        paginationVo.setDataList(list);
        paginationVo.setTotal(total);
        return paginationVo;
    }

    /**
     * 市场活动删除操作
     * @param id
     * @return
     */
    @Override
    public boolean doDelete(String[] id) {
        /*
            备注表和市场活动表相关联，删除市场活动时应将属于该市场活动的备注都删除，
         先将要删出的备注查找出来，删除备注，得到受影响的行数，再删除市场活动，与得到的受影响的行数相对比
         */
        boolean flag = true;
        int count1 = activityRemarkDao.getCountByIds(id);
        int count2 = activityRemarkDao.doDelete(id);
        if (count1 != count2){
            flag = false;
        }

        int count3 = activityDao.doDelete(id);
        if (count3 != id.length){
            flag = false;
        }
        return flag;
    }

    @Override
    public Activity selectActivityById(String id) {
        Activity activity = activityDao.selectActivityById(id);
        return activity;
    }

    @Override
    public int doEdit(Activity activity) {
        int n = activityDao.doEdit(activity);
        return n;
    }

    @Override
    public List<ActivityRemark> selectActivityRemarkById(String id) {
        List<ActivityRemark> remarks = activityRemarkDao.selectActivityRemarkById(id);
        return remarks;
    }

    @Override
    public boolean doSaveRemark(ActivityRemark activityRemark) {

        int i =  activityRemarkDao.doSaveRemark(activityRemark);
        if (i == 1){
            return true ;
        }
        return false;
    }

    @Override
    public List<Activity> getActivityListByNameAndNotInClueId(Map<String, String> map) {
        return activityDao.getActivityListByNameAndNotInClueId(map);
    }

    @Override
    public List<Activity> getActivityByClueId(String id) {
        return activityDao.getActivityByClueId(id);
    }

}

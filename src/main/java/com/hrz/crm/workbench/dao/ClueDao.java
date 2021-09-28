package com.hrz.crm.workbench.dao;

import com.hrz.crm.workbench.entity.Activity;
import com.hrz.crm.workbench.entity.Clue;

import java.util.List;
import java.util.Map;


public interface ClueDao {
    //保存线索
    int saveClue(Clue clue);
    //获取条件下记录条数
    int getCountByCondition(Map<String, Object> map);
    //获取线索列表
    List<Clue> doPageList(Map<String, Object> map);
    //按照id获取线索
    Clue getClueById(String id);
    //按照id获取关联的活动
    //List<Activity> getActivityByClueId(String id);

    //List<Activity> getActivityListByNameAndNotInClueId(Map<String, String> map);
}

package com.hrz.crm.workbench.dao;

import com.hrz.crm.workbench.entity.Clue;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/05/25/18:36
 * @Description:
 */
public interface ClueDao {
    //保存线索
    int saveClue(Clue clue);
    //获取条件下记录条数
    int getCountByCondition(Map<String, Object> map);
    //获取线索列表
    List<Clue> doPageList(Map<String, Object> map);
}

package com.hrz.crm.workbench.dao;

import com.hrz.crm.workbench.entity.ClueActivityRelation;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/09/14/22:25
 * @Description:
 */
public interface ClueActivityRelationDao {
    List<ClueActivityRelation> getListByClueId(String clueId);
}

package com.hrz.crm.workbench.dao;

import com.hrz.crm.workbench.entity.ClueRemark;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/09/14/22:10
 * @Description:
 */
public interface ClueRemarkDao {
    List<ClueRemark> getListByClueId(String clueId);
}

package com.hrz.crm.workbench.service;

import com.hrz.crm.vo.PaginationVo;
import com.hrz.crm.workbench.entity.Activity;
import com.hrz.crm.workbench.entity.Clue;
import com.hrz.crm.workbench.entity.Tran;

import java.util.List;
import java.util.Map;


public interface ClueService {
    //保存操作
    boolean doSave(Clue clue);
    //分页查询
    PaginationVo doPageList(Map<String, Object> map);
    //通过ID获取线索
    Clue getClueById(String id);
    //删除线索
    boolean deleteById(String[] id);

    boolean convert(String clueId, Tran tran, String createBy);
}

package com.hrz.crm.workbench.service;

import com.hrz.crm.vo.PaginationVo;
import com.hrz.crm.workbench.entity.Clue;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/05/25/9:11
 * @Description:
 */
public interface ClueService {
    boolean doSave(Clue clue);

    PaginationVo doPageList(Map<String, Object> map);
}

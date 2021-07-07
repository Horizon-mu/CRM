package com.hrz.crm.workbench.service.impl;

import com.hrz.crm.vo.PaginationVo;
import com.hrz.crm.workbench.dao.ClueDao;
import com.hrz.crm.workbench.entity.Clue;
import com.hrz.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/05/25/9:12
 * @Description:
 */
@Service
public class ClueServiceImpl implements ClueService {
    @Autowired
    private ClueDao clueDao;
    @Override
    public boolean doSave(Clue clue) {
        int n = clueDao.saveClue(clue);
        if (1 == n){
            return true;
        }
        return false;
    }

    @Override
    public PaginationVo doPageList(Map<String, Object> map) {
        //得到记录条数
        int total = clueDao.getCountByCondition(map);
        //得到线索列表
        List<Clue> list = clueDao.doPageList(map);
        PaginationVo<Clue> paginationVo = new PaginationVo<>();

        paginationVo.setTotal(total);
        paginationVo.setDataList(list);
        return paginationVo;
    }
}

package com.hrz.crm.workbench.service.impl;

import com.hrz.crm.workbench.entity.Tran;
import com.hrz.crm.workbench.entity.TranHistory;
import com.hrz.crm.workbench.service.TranService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/09/14/22:40
 * @Description:
 */
@Service
public class TranServiceImpl implements TranService {
    @Override
    public boolean save(Tran tran, String customerName) {
        return false;
    }

    @Override
    public Tran detail(String id) {
        return null;
    }

    @Override
    public List<TranHistory> getHistoryListByTranId(String tranId) {
        return null;
    }

    @Override
    public boolean changeStage(Tran tran) {
        return false;
    }

    @Override
    public Map<String, Object> getCharts() {
        return null;
    }
}

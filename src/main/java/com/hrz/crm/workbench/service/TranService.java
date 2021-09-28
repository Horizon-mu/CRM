package com.hrz.crm.workbench.service;

import com.hrz.crm.workbench.entity.Tran;
import com.hrz.crm.workbench.entity.TranHistory;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/09/14/22:40
 * @Description:
 */
public interface TranService {
    boolean save(Tran tran, String customerName);

    Tran detail(String id);

    List<TranHistory> getHistoryListByTranId(String tranId);

    boolean changeStage(Tran tran);

    Map<String, Object> getCharts();
}

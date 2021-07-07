package com.hrz.crm.settings.service;

import com.hrz.crm.settings.entity.DicValue;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/05/25/10:45
 * @Description:
 */
public interface DicService {
    Map<String, List<DicValue>> getAll();
}

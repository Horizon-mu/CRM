package com.hrz.crm.settings.dao;

import com.hrz.crm.settings.entity.DicType;
import com.hrz.crm.settings.entity.DicValue;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/05/25/11:01
 * @Description:
 */
public interface DicValueDao {
    List<DicValue> getValueList(String t);
}

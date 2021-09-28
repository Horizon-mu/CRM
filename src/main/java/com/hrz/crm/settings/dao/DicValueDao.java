package com.hrz.crm.settings.dao;

import com.hrz.crm.settings.entity.DicType;
import com.hrz.crm.settings.entity.DicValue;

import java.util.List;


public interface DicValueDao {
    List<DicValue> getValueList(String t);
}

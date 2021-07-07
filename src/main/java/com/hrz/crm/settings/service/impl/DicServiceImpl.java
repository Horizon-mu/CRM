package com.hrz.crm.settings.service.impl;

import com.hrz.crm.settings.dao.DicTypeDao;
import com.hrz.crm.settings.dao.DicValueDao;
import com.hrz.crm.settings.entity.DicType;
import com.hrz.crm.settings.entity.DicValue;
import com.hrz.crm.settings.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/05/25/10:45
 * @Description:
 */
@Service
public class DicServiceImpl implements DicService {
    @Autowired
    private DicValueDao dicValueDao;
    @Autowired
    private DicTypeDao dicTypeDao;
    @Override
    public Map<String, List<DicValue>> getAll() {
        //先获取所有的类型

        List<DicType> typeList = dicTypeDao.getTypeList();
        for(DicType d : typeList){
            System.out.println(d);
        }
        Map<String, List<DicValue>> map = new HashMap<>();
        //按照类型获取相应的value
        for(DicType t : typeList){
            String code = t.getCode();
            List<DicValue> valueList = dicValueDao.getValueList(code);
            map.put(code,valueList);
        }
        return map;
    }
}

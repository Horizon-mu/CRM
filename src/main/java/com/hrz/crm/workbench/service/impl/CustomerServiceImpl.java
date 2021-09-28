package com.hrz.crm.workbench.service.impl;

import com.hrz.crm.workbench.dao.CustomerDao;
import com.hrz.crm.workbench.entity.Customer;
import com.hrz.crm.workbench.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/09/14/21:46
 * @Description:
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;


    @Override
    public List<String> getCustomerName(String name) {
        return null;
    }
}

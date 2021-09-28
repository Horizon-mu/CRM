package com.hrz.crm.workbench.service;

import com.hrz.crm.workbench.entity.Customer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/09/14/21:45
 * @Description:
 */
public interface CustomerService {
    //Customer getCustomerByName(String name);

    List<String> getCustomerName(String name);
}

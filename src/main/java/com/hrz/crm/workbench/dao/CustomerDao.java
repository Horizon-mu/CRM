package com.hrz.crm.workbench.dao;

import com.hrz.crm.workbench.entity.Customer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/09/14/21:42
 * @Description:
 */
public interface CustomerDao {
    //List<String> getCustomerByName(String name);
    Customer getCustomerByName(String name);
    int save(Customer customer);
}

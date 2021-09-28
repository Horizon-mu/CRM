package com.hrz.crm.workbench.entity;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/09/14/21:01
 * @Description:
 */
@Data
public class Customer {
    /**
     * id
     */
    private String id;
    /**
     * 拥有者
     */
    private String owner;
    /**
     * 名字
     */
    private String name;
    private String website;
    private String phone;
    private String createBy;
    private String createTime;
    private String editBy;
    private String editTime;
    /**
     * 纪要
     */
    private String contactSummary;
    private String nextContactTime;
    private String description;
    private String address;

}

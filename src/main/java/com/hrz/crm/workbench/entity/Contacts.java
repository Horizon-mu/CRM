package com.hrz.crm.workbench.entity;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/09/14/21:06
 * @Description:
 */
@Data
public class Contacts {
           private String id;
           private String owner;
           private String source;
           private String customerId;
           private String fullname;
           private String appellation;
           private String email;
           private String mphone;
           private String job;
           private String birth;
           private String createBy;
           private String createTime;
           private String editBy;
           private String editTime;
           private String description;
           private String contactSummary;
           private String nextContactTime;
           private String address;

}

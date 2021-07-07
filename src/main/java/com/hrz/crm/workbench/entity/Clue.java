package com.hrz.crm.workbench.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/05/25/18:06
 * @Description:
 */
@Data
@ToString
@NoArgsConstructor
public class Clue {
    private String id;
    private String fullname;
    private String appellation;
    private String owner;
    private String company;
    private String job;
    private String email;
    private String phone;
    private String website;
    private String mphone;
    private String state ;
    private String source;
    private String createBy;
    private String createTime;
    private String editBy ;
    private String editTime;
    private String description;
    private String contactSummary;
    private String nextContactTime;
    private String address;

}

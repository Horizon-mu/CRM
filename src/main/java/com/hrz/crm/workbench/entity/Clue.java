package com.hrz.crm.workbench.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@NoArgsConstructor
public class Clue {
    /**
     * 线索id
     */
    private String id;
    /**
     * 全名
     */
    private String fullname;
    /**
     * 称呼
     */
    private String appellation;
    /**
     * 拥有者
     */
    private String owner;
    /**
     * 所属公司
     */
    private String company;
    /**
     * 职位
     */
    private String job;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 公司电话
     */
    private String phone;
    /**
     * 公司网站
     */
    private String website;
    /**
     * 私人电话
     */
    private String mphone;
    /**
     * 状态
     */
    private String state ;
    /**
     * 来源
     */
    private String source;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改者
     */
    private String editBy ;
    /**
     * 修改时间
     */
    private String editTime;
    /**
     * 描述
     */
    private String description;
    /**
     * 纪要
     */
    private String contactSummary;

    /**
     * 下次联系时间
     */
    private String nextContactTime;
    /**
     * 地址
     */
    private String address;

}

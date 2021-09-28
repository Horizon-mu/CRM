package com.hrz.crm.workbench.entity;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/09/14/21:07
 * @Description:
 */
@Data
public class Tran {
    /**
     * 主键id
     */
    private String id;
    /**
     * 拥有者
     */
    private String owner;
    /**
     *
     */
    private String money;
    /**
     * 名称
     */
    private String name;
    /**
     * 期望日期
     */
    private String expectedDate;
    /**
     * 顾客id
     */
    private String customerId;
    /**
     * 阶段
     */
    private String stage;
    /**
     * 类型
     */
    private String type;
    /**
     * 来源
     */
    private String source;
    /**
     * 市场活动id
     */
    private String activityId;
    /**
     * 联系ID
     */
    private String contactsId;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 编辑
     */
    private String editBy;
    /**
     * 编辑时间
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

    public void setPossibility(String possibility) {
    }
}

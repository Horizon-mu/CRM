package com.hrz.crm.workbench.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@ToString
public class Activity {
    private String  id ;                  // id 主键
    private String  owner;                // 所有者，外键 UUID 关联tbl_user表
    private String  name;                 // 活动名称
    private String  startDate;            // 开始时间
    private String  endDate;              // 结束时间
    private String  cost;                 // 花费
    private String  description;          // 活动描述
    private String  createTime;           // 创建时间
    private String  createBy;             // 创建者
    private String  editTime;             // 修改时间
    private String  editBy;               // 修改者
}

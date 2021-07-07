package com.hrz.crm.workbench.entity;

import lombok.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/05/11/15:56
 * @Description:
 */
@Data
@NoArgsConstructor
@ToString
public class ActivityRemark {
   private String id;               //主键
   private String noteContent;      // 备注
   private String createTime;
   private String createBy;
   private String editTime;
   private String editBy;
   private String editFlag;                  //修改标记（是否被修改过）
   private String activityId;               //外键

}

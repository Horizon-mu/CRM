package com.hrz.crm.workbench.entity;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/09/14/21:12
 * @Description:
 */
@Data
public class ClueRemark {
            private String id;
            private String noteContent;
            private String createBy;
            private String createTime;
            private String editBy;
            private String editTime;
            private String editFlag;
            private String clueId;


}

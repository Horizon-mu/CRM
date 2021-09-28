package com.hrz.crm.workbench.entity;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/09/14/21:13
 * @Description:
 */
@Data
public class ContactsRemark {
            private String id;
            private String noteContent;
            private String createBy;
            private String createTime;
            private String editBy;
            private String editTime;
            private String editFlag;
            private String contactsId;

}

package com.hrz.crm.workbench.entity;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/09/14/21:08
 * @Description:
 */
@Data
public class TranHistory {

    private String id;
    private String stage;
    private String money;
    private String expectedDate;
    private String createTime;
    private String createBy;
    private String tranId;

    public void setPossibility(String possibility) {

    }
}

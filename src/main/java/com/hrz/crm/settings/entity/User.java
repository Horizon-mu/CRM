package com.hrz.crm.settings.entity;

import lombok.*;


@Data
@NoArgsConstructor
@ToString

/*

 */
public class User {
    private String id;                // id编号 主键
    private String loginAct;          // 登录账号
    private String name;              // 用户真实姓名
    private String loginPwd;          // 登录密码
    private String email;             // 用户邮箱
    private String expireTime;        // 过期时间
    private String lockState ;        // 锁定状态 0 锁定 1 正常
    private String deptno;            // 部门编号
    private String allowIps;          // 允许登录IP
    private String createTime;        // 创建时间
    private String createBy;          // 创建者
    private String editTime;          // 编辑时间
    private String editBy;            // 编辑者
}

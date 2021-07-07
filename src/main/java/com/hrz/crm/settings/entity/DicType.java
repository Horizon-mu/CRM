package com.hrz.crm.settings.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/05/25/9:14
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DicType {
    private String code;
    private String name;
    private String description;

}

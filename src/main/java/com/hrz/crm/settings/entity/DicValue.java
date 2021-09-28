package com.hrz.crm.settings.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DicValue {
    private String id;
    private String value;
    private String text;
    private String orderNo;
    private String typeCode;

}

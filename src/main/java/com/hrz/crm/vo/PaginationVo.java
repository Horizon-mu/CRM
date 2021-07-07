package com.hrz.crm.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/05/12/17:01
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationVo<T> {
    private int total;
    private List<T> dataList;
}

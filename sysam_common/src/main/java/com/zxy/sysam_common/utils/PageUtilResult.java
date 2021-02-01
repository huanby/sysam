package com.zxy.sysam_common.utils;

import java.util.List;

/**
 * 返回结果集工具类
 * total 结构集总数
 * row	结构集
 *
 * @param <T>
 * @since 2021-01-28
 */
public class PageUtilResult<T> {

    private Long total;
    private List<T> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}

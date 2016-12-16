package com.cjgod.candy.model;


import java.util.List;

/**
 * Created by Administrator on 2016/9/21.
 */
public class PageResult {
    private Integer total;
    private List<?> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}

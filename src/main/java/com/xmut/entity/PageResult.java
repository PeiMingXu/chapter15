package com.xmut.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author
 * @date: 2023/4/26
 **/
public class PageResult implements Serializable {
    private long total;//分页总数
    private List rows;//返回的数据总和

    public PageResult(long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}

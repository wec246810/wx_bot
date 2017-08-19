package com.ysk.dto;

import java.util.List;

/**
 * Created by Y.S.K on 2017/8/8 in wx_bot.
 */
public class EUDataGridResult {
    private long total;
    private List<?> rows;
    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
    public List<?> getRows() {
        return rows;
    }
    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "EUDataGridResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}

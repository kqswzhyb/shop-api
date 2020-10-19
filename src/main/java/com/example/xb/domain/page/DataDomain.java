package com.example.xb.domain.page;

import java.util.List;

public class DataDomain {
    /** 当前页 */
    private String current;
    /** 每页条数 */
    private String size;
    /** 总记录数 */
    private long total;
    /** 列表数据 */
    private List<?> records;

    public void setCurrent(Object current) {
        this.current = current.toString();
    }

    public void setSize(Object size) {
        this.size = size.toString();
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setRecords(List<?> records) {
        this.records = records;
    }

    public String getSize() {
        return size;
    }

    public long getTotal() {
        return total;
    }

    public String getCurrent() {
        return current;
    }

    public List<?> getRecords() {
        return records;
    }
}

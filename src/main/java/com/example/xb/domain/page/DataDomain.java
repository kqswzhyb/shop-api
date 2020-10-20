package com.example.xb.domain.page;

import lombok.Data;

import java.util.List;

@Data
public class DataDomain {
    /** 当前页 */
    private String current = "1";
    /** 每页条数 */
    private String size = "10";
    /** 总记录数 */
    private long total = 0;
    /** 列表数据 */
    private List<?> records;
}

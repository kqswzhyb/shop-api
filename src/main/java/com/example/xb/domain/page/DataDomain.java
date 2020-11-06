package com.example.xb.domain.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Data
@AllArgsConstructor
public class DataDomain {
    /** 当前页 */
    private int current = 1;
    /** 每页条数 */
    private int pageSize = 10;
    /** 总记录数 */
    private long total = 0;
    /** 列表数据 */
    private List<?> records;

    public DataDomain(String current,String pageSize){
        this.current=!StringUtils.isEmptyOrWhitespace(current) ?  Integer.parseInt(current) : 1;
        this.pageSize=!StringUtils.isEmptyOrWhitespace(pageSize) ? Integer.parseInt(pageSize) : 10;
    }
}

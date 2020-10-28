package com.example.xb.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 */
@Data
public class Base implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 创建者 */
    @ExcelIgnore
    private String createBy;

    /** 创建时间 */
    @ExcelIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createAt;

    /** 更新时间 */
    @ExcelIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateAt;

    /** 备注 */
    @ColumnWidth(50)
    @ExcelProperty("备注")
    private String remark;
}

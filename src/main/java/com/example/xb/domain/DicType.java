package com.example.xb.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DicType extends Base {
    /**
     * 字典类型ID
     */
    private String typeId;

    /**
     * 字典类型名称
     */
    private String name;
    /**
     * 字典类型编码
     */
    private String code;

    /**
     * 状态（0 正常  1 停用)
     */
    private  String status;
}

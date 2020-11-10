package com.example.xb.domain.dic;

import com.example.xb.domain.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Dic extends Base {
    /**
     * 字典ID
     */
    private String dicId;
    /**
     * 字典类型ID
     */
    private String typeId;

    /**
     * 字典名称
     */
    private String name;
    /**
     * 字典值
     */
    private String value;

    /**
     * 状态（0 正常  1 停用)
     */
    private  String status;
}

package com.example.xb.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AttrSon extends Base{
    private String attrSonId;

    private String attrId;
    /**
     * 产品属性详情如蓝色，15.6寸
     */
    private String name;

    private String value;

    private String status;
}

package com.example.xb.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AttrBase extends Base {

    private String attrId;

    private String productId;
    /**
     * 产品属性如颜色，尺寸
     */
    private String attrName;
    /**
     * 产品属性如颜色，尺寸对应的code
     */
    private String attrValue;

    private String status;

    private List<AttrSon> attrSonList;
}

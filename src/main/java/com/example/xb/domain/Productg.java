package com.example.xb.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Productg extends Base {

    private String productgId;

    private String productId;
    /**
     * 产品价格
     */
    private BigDecimal price;

    private List<String> attrList;

    private String status;
}

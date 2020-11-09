package com.example.xb.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductgStock extends Base {

    private String stockId;

    private String productgId;
    /**
     * 产品总库存
     */
    private BigDecimal totalStock;
    /**
     * 产品锁定库存
     */
    private BigDecimal lockStock;

    private String status;
}

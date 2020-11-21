package com.example.xb.domain.vo;

import com.example.xb.domain.product.Product;
import com.example.xb.domain.product.Productg;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductgVo extends Productg {
    /**
     * 产品总库存
     */
    private BigDecimal totalStock;
    /**
     * 产品锁定库存
     */
    private BigDecimal lockStock;

    private Product product;
}

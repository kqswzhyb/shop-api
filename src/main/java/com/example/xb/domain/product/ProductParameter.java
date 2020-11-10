package com.example.xb.domain.product;

import lombok.Data;

@Data
/**
 * 产品参数关联
 */
public class ProductParameter {

    private String productId;

    private String parameterId;

    private String content;
}

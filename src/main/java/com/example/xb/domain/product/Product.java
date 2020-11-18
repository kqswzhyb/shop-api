package com.example.xb.domain.product;

import com.example.xb.domain.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Product extends Base {
    private String productId;

    private String productCode;

    private String name;

    private String productUnit;

    private String brandId;

    private String status;

    private String saleStatus;
}

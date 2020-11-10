package com.example.xb.domain.product;

import com.example.xb.domain.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductDes extends Base {

    private String desId;

    private String productId;

    private String desName;

    private String content;

    private String status;
}

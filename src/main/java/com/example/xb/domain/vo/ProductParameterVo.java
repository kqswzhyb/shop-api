package com.example.xb.domain.vo;

import com.example.xb.domain.product.ProductParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductParameterVo extends ProductParameter {

    private String ParameterName;

    private String ParameterCode;
}

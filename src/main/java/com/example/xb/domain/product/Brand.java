package com.example.xb.domain.product;

import com.example.xb.domain.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Brand  extends Base {

    private String brandId;

    private String brandCode;

    private String name;

    private String status;
}

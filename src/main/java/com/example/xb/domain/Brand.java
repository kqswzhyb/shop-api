package com.example.xb.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Brand  extends Base{

    private String brandId;

    private String brandCode;

    private String name;

    private String status;
}

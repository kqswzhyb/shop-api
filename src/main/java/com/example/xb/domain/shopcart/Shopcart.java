package com.example.xb.domain.shopcart;

import com.example.xb.domain.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class Shopcart extends Base {

    private String shopcartId;

    private String productgId;

    private String userId;

    private BigDecimal cartNumber;

    private String status;
}

package com.example.xb.domain.product;

import com.example.xb.domain.Base;
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

    private List<AttrDetail> attrList;

    private String status;
}

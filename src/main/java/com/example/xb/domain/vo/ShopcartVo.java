package com.example.xb.domain.vo;

import com.example.xb.domain.product.Product;
import com.example.xb.domain.shopcart.Shopcart;
import lombok.Data;

@Data
public class ShopcartVo  extends Shopcart {

    private ProductgVo productgVo;
}

package com.example.xb.domain.order;

import com.example.xb.domain.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderDetail extends Base {
    /**
     * 订单详情id
     */
    private String orderDetailId;
    /**
     * 订单Id
     */
    private String orderId;
    /**
     * 产品id
     */
    private String productId;
    /**
     * 产品组id
     */
    private String productgId;
    /**
     * 产品编码
     */
    private String productCode;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 品牌id
     */
    private String brandId;
    /**
     * 品牌名称
     */
    private String brandName;
    /**
     * 产品价格
     */
    private BigDecimal originPrice;
    /**
     * 购买数量
     */
    private Integer productNum;
    /**
     * 产品单位
     */
    private String productUnit;
    /**
     * 属性组
     */
    private String attrs;
    /**
     * 状态
     */
    private String status;
}

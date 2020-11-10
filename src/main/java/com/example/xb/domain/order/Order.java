package com.example.xb.domain.order;

import com.example.xb.domain.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class Order extends Base {
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 订单编码
     */
    private String orderCode;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 联系人姓名
     */
    private String contactName;
    /**
     * 联系人电话
     */
    private String contactPhone;
    /**
     * 地址-省id
     */
    private String provinceId;
    /**
     * 地址-省
     */
    private String provinceName;
    /**
     * 地址-市id
     */
    private String cityId;
    /**
     * 地址-市
     */
    private String cityName;
    /**
     * 地址-区id
     */
    private String areaId;
    /**
     * 地址-区
     */
    private String areaName;
    /**
     * 详细地址
     */
    private String addressDetail;
    /**
     * 支付方式
     */
    private String paymentMethod;
    /**
     * 是否需要开票
     */
    private String isInvoice;
    /**
     * 运费
     */
    private BigDecimal freight;
    /**
     * 产品总价
     */
    private BigDecimal productTotalAmount;
    /**
     * 订单总价
     */
    private BigDecimal orderTotalAmount;
    /**
     * 实际付款金额
     */
    private BigDecimal actualPayAmount;
    /**
     * 订单状态
     */
    private String orderStatus;
}

package com.example.xb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    CREATE("0", "订单创建"),
    CANCEL("1", "订单取消"),
    EXPIRE("2", "订单付款过期"),
    PART("3", "部分付款"),
    FULL("4", "完全付款"),
    DELIVER("5", "已发货"),
    COLLECT("6", "待收货"),
    ACCEPT("7", "已收货"),
    REFUSE("8", "拒绝收货");

    private final String value;
    private final String description;
}

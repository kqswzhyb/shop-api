package com.example.xb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethod {
    ALIPAY("0", "支付宝"),
    WECHAT("1", "微信支付");

    private final String value;
    private final String description;
}

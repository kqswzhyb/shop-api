package com.example.xb.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum Code {
    OK("0", "操作成功"),
    ERROR("1", "操作失败");

    private final String code;
    private final String info;


}

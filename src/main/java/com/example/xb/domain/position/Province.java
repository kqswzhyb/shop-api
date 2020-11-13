package com.example.xb.domain.position;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;


/**
 * 省
 */
@Data
@TableName("sys_provinces")
public class Province {

    private String id;

    private String provinceId;

    private String province;

    private String countryId;

    private String provinceEn;
}

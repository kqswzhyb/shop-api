package com.example.xb.domain.user;

import com.example.xb.domain.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserAddress extends Base {

    private String addressId;

    private String userId;

    private String provinceId;

    private String provinceName;

    private String cityId;

    private String cityName;

    private String areaId;

    private String areaName;

    private String detail;

    private String status;
}

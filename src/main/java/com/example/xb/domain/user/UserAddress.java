package com.example.xb.domain.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.xb.domain.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("user_address")
public class UserAddress extends Base {

    @TableId("address_id")
    private String addressId;
    private String userId;

    private String provinceId;

    private String provinceName;

    private String cityId;

    private String cityName;

    private String areaId;

    private String areaName;

    private String addressDetail;

    private String contactName;

    private String contactPhone;

    private String status;
}

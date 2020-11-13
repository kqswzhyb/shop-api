package com.example.xb.domain.position;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_cities")
//市
public class City {

    private String id;

    private String cityId;

    private String city;

    private String provinceId;

    private String cityEn;
}

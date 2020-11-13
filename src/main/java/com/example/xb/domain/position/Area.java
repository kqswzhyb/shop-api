package com.example.xb.domain.position;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_areas")
//区
public class Area {

    private String id;

    private String areaId;

    private String area;

    private String cityId;

    private String areaEn;
}

package com.example.xb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.xb.domain.position.Area;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface SysAreasMapper extends BaseMapper<Area> {

    List<Area> areaList(String cityId);
}

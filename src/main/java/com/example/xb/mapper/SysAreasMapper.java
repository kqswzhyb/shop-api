package com.example.xb.mapper;

import com.example.xb.domain.position.Area;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysAreasMapper {

    List<Area> areaList(String cityId);
}

package com.example.xb.mapper;

import com.example.xb.domain.position.City;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysCitiesMapper {

    List<City> cityList(String provinceId);
}

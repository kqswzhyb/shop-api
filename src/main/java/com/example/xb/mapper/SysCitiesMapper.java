package com.example.xb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.xb.domain.position.City;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface SysCitiesMapper extends BaseMapper<City> {

    List<City> cityList(String provinceId);
}

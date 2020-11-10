package com.example.xb.service.impl;

import com.example.xb.domain.position.Area;
import com.example.xb.domain.position.City;
import com.example.xb.domain.position.Province;
import com.example.xb.mapper.SysAreasMapper;
import com.example.xb.mapper.SysCitiesMapper;
import com.example.xb.mapper.SysProvincesMapper;
import com.example.xb.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private SysCitiesMapper sysCitiesMapper;
    @Autowired
    private SysAreasMapper sysAreasMapper;
    @Autowired
    private SysProvincesMapper sysProvincesMapper;

    @Override
    public List<Area> areaList(String cityId) {
        return sysAreasMapper.areaList(cityId);
    }

    @Override
    public List<City> cityList(String provinceId) {
        return sysCitiesMapper.cityList(provinceId);
    }

    @Override
    public List<Province> provinceList(String countryId) {
        return sysProvincesMapper.provinceList(countryId);
    }
}

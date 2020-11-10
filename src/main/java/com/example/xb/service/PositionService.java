package com.example.xb.service;

import com.example.xb.domain.position.Area;
import com.example.xb.domain.position.City;
import com.example.xb.domain.position.Province;

import java.util.List;

public interface PositionService {
    /**
     * 查区
     * @param cityId
     * @return
     */
    List<Area> areaList(String cityId);

    /**
     * 查市
     * @param provinceId
     * @return
     */
    List<City> cityList(String provinceId);

    /**
     * 查省
     * @param countryId
     * @return
     */
    List<Province> provinceList(String countryId);
}

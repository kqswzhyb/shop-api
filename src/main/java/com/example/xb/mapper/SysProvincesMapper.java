package com.example.xb.mapper;

import com.example.xb.domain.position.Province;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysProvincesMapper {
    /**
     * 根据国家Id查找省
     * @param countryId
     * @return
     */
    List<Province> provinceList(String countryId);
}

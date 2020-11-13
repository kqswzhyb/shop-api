package com.example.xb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.xb.domain.position.Province;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface SysProvincesMapper extends BaseMapper<Province> {
    /**
     * 根据国家Id查找省
     * @param countryId
     * @return
     */
    List<Province> provinceList(String countryId);
}

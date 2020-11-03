package com.example.xb.mapper;

import com.example.xb.domain.Brand;
import com.example.xb.domain.vo.BrandVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandMapper {
    /**
     * 获取品牌列表
     * @param brand
     * @return
     */
    List<BrandVo> brandList(Brand brand);
}

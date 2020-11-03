package com.example.xb.service;

import com.example.xb.domain.Brand;
import com.example.xb.domain.vo.BrandVo;

import java.util.List;

public interface BrandService {
    /**
     * 获取品牌列表
     * @param brand
     * @return
     */
    List<BrandVo> brandList(Brand brand);
}

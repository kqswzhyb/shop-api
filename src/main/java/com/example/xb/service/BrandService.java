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

    /**
     * 创建新品牌
     * @param brandVo
     * @return
     */
    int saveBrand(BrandVo brandVo);

    /**
     * 更新品牌
     * @param brandVo
     * @return
     */
    int updateBrand(BrandVo brandVo);

    /**
     * 通过brandId删除品牌
     *
     * @param brandId 品牌ID
     * @return 结果
     */
    int deleteBrandById(String brandId);
}

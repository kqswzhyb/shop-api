package com.example.xb.mapper;

import com.example.xb.domain.product.Productg;
import com.example.xb.domain.vo.ProductgVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface ProductgMapper {
    /**
     * 获取产品组列表
     * @param
     * @return
     */
    List<Productg> productgList(String productId);

    /**
     * 获取产品组（包括产品）列表
     * @param
     * @return
     */
    List<ProductgVo> productgListAll(String productId);

    /**
     * 批量创建产品组
     * @param list
     * @return
     */
    int batchSaveProductg(List<Productg> list);

    /**
     * 删除产品组
     * @param productId
     * @return
     */
    int deleteProductgById(String productId);
}

package com.example.xb.mapper;

import com.example.xb.domain.product.ProductAttr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductAttrMapper {
    /**
     * 获取产品属性详情关系列表
     * @param
     * @return
     */
    List<ProductAttr> productAttrList(String productgId);

    /**
     * 批量创建产品属性详情关系
     * @param list
     * @return
     */
    int batchSaveProductAttr(List<ProductAttr> list);

    /**
     * 删除产品属性详情关系
     * @param productgId
     * @return
     */
    int deleteProductAttrById(String productgId);
}

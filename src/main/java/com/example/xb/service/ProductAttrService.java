package com.example.xb.service;

import com.example.xb.domain.ProductAttr;

import java.util.List;

public interface ProductAttrService {
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

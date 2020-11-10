package com.example.xb.service;

import com.example.xb.domain.product.AttrBase;

import java.util.List;

public interface AttrBaseService {
    /**
     * 获取产品属性列表
     * @param
     * @return
     */
    List<AttrBase> attrBaseList(String productId);

    /**
     * 批量创建产品属性
     * @param list
     * @return
     */
    int batchSaveAttrBase(List<AttrBase> list);

    /**
     * 删除产品属性
     * @param productId
     * @return
     */
    int deleteAttrBaseById(String productId);
}

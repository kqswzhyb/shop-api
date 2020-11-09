package com.example.xb.service;

import com.example.xb.domain.Productg;

import java.util.List;

public interface ProductgService {
    /**
     * 获取产品组列表
     * @param
     * @return
     */
    List<Productg> productgList(String productId);

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

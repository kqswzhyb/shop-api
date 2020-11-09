package com.example.xb.service;

import com.example.xb.domain.ProductgStock;

import java.math.BigDecimal;
import java.util.List;

public interface ProductgStockService {
    /**
     * 指定型号产品的库存
     * @param productgId
     * @return
     */
    List<ProductgStock> productgStockList(String productgId);

    /**
     * 创建指定型号产品的库存
     * @param productgStock
     * @return
     */
    int saveProductgStock(ProductgStock productgStock);


    /**
     * 更新指定型号产品的总库存
     * @param totalStock
     * @return
     */
    int updateTotalStock(BigDecimal totalStock);

    /**
     * 更新指定型号产品的锁定库存
     * @param lockStock
     * @return
     */
    int updateLockStock(BigDecimal lockStock);

    /**
     * 删除指定型号产品的库存记录
     * @param productgId
     * @return
     */
    int deleteStock(String productgId);
}

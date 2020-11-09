package com.example.xb.service.impl;

import com.example.xb.domain.ProductgStock;
import com.example.xb.service.ProductgStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductgStockServiceImpl implements ProductgStockService{
    @Autowired
    private ProductgStockService productgStockService;

    @Override
    public List<ProductgStock> productgStockList(String productgId) {
        return productgStockService.productgStockList(productgId);
    }

    @Override
    public int saveProductgStock(ProductgStock productgStock) {
        return productgStockService.saveProductgStock(productgStock);
    }

    @Override
    public int updateTotalStock(BigDecimal totalStock) {
        return productgStockService.updateTotalStock(totalStock);
    }

    @Override
    public int updateLockStock(BigDecimal lockStock) {
        return productgStockService.updateLockStock(lockStock);
    }

    @Override
    public int deleteStock(String productgId) {
        return productgStockService.deleteStock(productgId);
    }
}

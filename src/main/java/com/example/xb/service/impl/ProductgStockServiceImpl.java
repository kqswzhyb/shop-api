package com.example.xb.service.impl;

import com.example.xb.domain.product.ProductgStock;
import com.example.xb.mapper.ProductgStockMapper;
import com.example.xb.service.ProductgStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductgStockServiceImpl implements ProductgStockService{
    @Autowired
    private ProductgStockMapper productgStockMapper;

    @Override
    public List<ProductgStock> productgStockList(String productgId) {
        return productgStockMapper.productgStockList(productgId);
    }

    @Override
    public int saveProductgStock(ProductgStock productgStock) {
        return productgStockMapper.saveProductgStock(productgStock);
    }

    @Override
    public int updateTotalStock(BigDecimal totalStock) {
        return productgStockMapper.updateTotalStock(totalStock);
    }

    @Override
    public int updateLockStock(BigDecimal lockStock) {
        return productgStockMapper.updateLockStock(lockStock);
    }

    @Override
    public int deleteStock(String productgId) {
        return productgStockMapper.deleteStock(productgId);
    }
}

package com.example.xb.service.impl;

import com.example.xb.domain.product.ProductDes;
import com.example.xb.mapper.ProductDesMapper;
import com.example.xb.service.ProductDesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDesServiceImpl implements ProductDesService {
    @Autowired
    private ProductDesMapper productDesMapper;

    @Override
    public List<ProductDes> productDesList(String productId) {
        return productDesMapper.productDesList(productId);
    }

    @Override
    public int batchSaveProductDes(List<ProductDes> list) {
        return productDesMapper.batchSaveProductDes(list);
    }

    @Override
    public int deleteProductDesById(String productId) {
        return productDesMapper.deleteProductDesById(productId);
    }
}

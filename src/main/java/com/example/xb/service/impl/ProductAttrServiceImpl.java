package com.example.xb.service.impl;

import com.example.xb.domain.product.ProductAttr;
import com.example.xb.mapper.ProductAttrMapper;
import com.example.xb.service.ProductAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductAttrServiceImpl implements ProductAttrService {
    @Autowired
    private ProductAttrMapper productAttrMapper;
    @Override
    public List<ProductAttr> productAttrList(String productgId) {
        return productAttrMapper.productAttrList(productgId);
    }

    @Override
    public int batchSaveProductAttr(List<ProductAttr> list) {
        return productAttrMapper.batchSaveProductAttr(list);
    }

    @Override
    public int deleteProductAttrById(String productgId) {
        return productAttrMapper.deleteProductAttrById(productgId);
    }
}

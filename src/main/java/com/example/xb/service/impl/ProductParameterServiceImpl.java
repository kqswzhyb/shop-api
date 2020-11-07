package com.example.xb.service.impl;

import com.example.xb.domain.ProductParameter;
import com.example.xb.domain.vo.ProductParameterVo;
import com.example.xb.mapper.ProductParameterMapper;
import com.example.xb.service.ProductParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductParameterServiceImpl implements ProductParameterService {
    @Autowired
    private ProductParameterMapper productParameterMapper;
    @Override
    public List<ProductParameterVo> productParameterList(String productId) {
        return productParameterMapper.productParameterList(productId);
    }

    @Override
    public int batchSaveProductParameter(List<ProductParameter> list) {
        return productParameterMapper.batchSaveProductParameter(list);
    }

    @Override
    public int deleteProductParameterById(String productId) {
        return productParameterMapper.deleteProductParameterById(productId);
    }
}

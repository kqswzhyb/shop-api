package com.example.xb.service.impl;

import com.example.xb.domain.Product;
import com.example.xb.domain.vo.ProductVo;
import com.example.xb.mapper.ProductMapper;
import com.example.xb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductVo> productList(Product product) {
        return productMapper.productList(product);
    }

    @Override
    public int saveProduct(Product product) {
        return productMapper.saveProduct(product);
    }

    @Override
    public int updateProduct(Product product) {
        return productMapper.updateProduct(product);
    }

    @Override
    public int deleteProductById(String productId) {
        return productMapper.deleteProductById(productId);
    }
}

package com.example.xb.service;

import com.example.xb.domain.Product;
import com.example.xb.domain.vo.ProductVo;

import java.util.List;

public interface ProductService {
    /**
     * 根据条件分页查询产品数据
     *
     * @param product 产品信息
     * @return 产品数据集合信息
     */
    List<ProductVo> productList(Product product);
}

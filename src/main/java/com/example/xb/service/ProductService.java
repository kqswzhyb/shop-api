package com.example.xb.service;

import com.example.xb.domain.product.Product;
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

    /**
     * 创建产品基础
     * @param product
     * @return
     */
    int saveProduct(Product product);

    /**
     * 更新产品基础
     * @param product
     * @return
     */
    int updateProduct(Product product);


    /**
     * 删除产品根据id
     * @param productId
     * @return
     */
    int deleteProductById(String productId);

}

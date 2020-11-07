package com.example.xb.service;

import com.example.xb.domain.ProductParameter;
import com.example.xb.domain.vo.ProductParameterVo;

import java.util.List;

public interface ProductParameterService {
    /**
     * 根据productId查询产品参数
     *
     * @param productId
     * @return
     */
    List<ProductParameterVo> productParameterList(String productId);

    /**
     * 批量创建产品参数
     * @param list
     * @return
     */
    int batchSaveProductParameter(List<ProductParameter> list);

    /**
     * 删除产品参数根据id
     * @param productId
     * @return
     */
    int deleteProductParameterById(String productId);
}

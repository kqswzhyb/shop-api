package com.example.xb.mapper;

import com.example.xb.domain.product.ProductParameter;
import com.example.xb.domain.vo.ProductParameterVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductParameterMapper {
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

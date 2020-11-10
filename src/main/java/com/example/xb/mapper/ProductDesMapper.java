package com.example.xb.mapper;

import com.example.xb.domain.product.ProductDes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDesMapper {
    /**
     * 根据productId查询产品描述
     *
     * @param productId
     * @return
     */
    List<ProductDes> productDesList(String productId);

    /**
     * 批量创建产品描述
     * @param list
     * @return
     */
    int batchSaveProductDes(List<ProductDes> list);

    /**
     * 删除产品描述根据id
     * @param productId
     * @return
     */
    int deleteProductDesById(String productId);
}

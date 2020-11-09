package com.example.xb.mapper;

import com.example.xb.domain.Productg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductgMapper {
    /**
     * 获取产品组列表
     * @param
     * @return
     */
    List<Productg> productgList(String productId);

    /**
     * 批量创建产品组
     * @param list
     * @return
     */
    int batchSaveProductg(List<Productg> list);

    /**
     * 删除产品组
     * @param productId
     * @return
     */
    int deleteProductgById(String productId);
}

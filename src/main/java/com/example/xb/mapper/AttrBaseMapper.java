package com.example.xb.mapper;

import com.example.xb.domain.AttrBase;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttrBaseMapper {
    /**
     * 获取产品属性列表
     * @param
     * @return
     */
    List<AttrBase> attrBaseList(String productId);

    /**
     * 批量创建产品属性
     * @param list
     * @return
     */
    int batchSaveAttrBase(List<AttrBase> list);

    /**
     * 删除产品属性
     * @param productId
     * @return
     */
    int deleteAttrBaseById(String productId);
}

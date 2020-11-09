package com.example.xb.mapper;

import com.example.xb.domain.AttrSon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttrSonMapper {
    /**
     * 获取产品属性详情列表
     * @param
     * @return
     */
    List<AttrSon> attrSonList(String attrId);

    /**
     * 批量创建产品属性详情
     * @param list
     * @return
     */
    int batchSaveAttrSon(List<AttrSon> list);

    /**
     * 删除产品属性详情
     * @param attrId
     * @return
     */
    int deleteAttrSonById(String attrId);
}

package com.example.xb.mapper;

import com.example.xb.domain.DicType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DicTypeMapper {
    /**
     * 获取字典类型列表
     * @param dicType
     * @return
     */
    List<DicType> dicTypeList(DicType dicType);

    /**
     * 创建字典类型
     * @param dicType
     * @return
     */
    int saveDicType(DicType dicType);

    /**
     * 更新字典类型
     * @param dicType
     * @return
     */
    int updateDicType(DicType dicType);

    /**
     * 删除字典类型
     * @param typeId
     * @return
     */
    int deleteDicTypeById(String typeId);
}

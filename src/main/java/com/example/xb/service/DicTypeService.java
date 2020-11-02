package com.example.xb.service;

import com.example.xb.domain.DicType;

import java.util.List;

public interface DicTypeService {
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

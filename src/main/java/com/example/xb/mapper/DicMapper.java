package com.example.xb.mapper;

import com.example.xb.domain.Dic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DicMapper {
    /**
     * 获取字典列表
     * @param dic
     * @return
     */
    List<Dic> dicList(Dic dic);

    /**
     * 根据typeId获取字典列表
     * @return
     */
    List<Dic> dicListByType(String typeId);

    /**
     * 创建字典类型
     * @param dic
     * @return
     */
    int saveDic(Dic dic);

    /**
     * 更新字典
     * @param dic
     * @return
     */
    int updateDic(Dic dic);

    /**
     * 删除字典
     * @param dicId
     * @return
     */
    int deleteDicById(String dicId);
}

package com.example.xb.service;

import com.example.xb.domain.Dic;

import java.util.List;

public interface DicService {
    /**
     * 获取字典列表
     * @param dic
     * @return
     */
    List<Dic> dicList(Dic dic);

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

package com.example.xb.service.impl;

import com.example.xb.domain.dic.DicType;
import com.example.xb.domain.vo.DiCTypeVo;
import com.example.xb.mapper.DicTypeMapper;
import com.example.xb.service.DicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DicTypeServiceImpl implements DicTypeService {

    @Autowired
    private DicTypeMapper dicTypeMapper;

    @Override
    public List<DicType> dicTypeList(DicType dicType) {
        return dicTypeMapper.dicTypeList(dicType);
    }

    @Override
    public List<DiCTypeVo> dicAllList() {
        return dicTypeMapper.dicAllList();
    }

    @Override
    public int saveDicType(DicType dicType) {
        return dicTypeMapper.saveDicType(dicType);
    }

    @Override
    public int updateDicType(DicType dicType) {
        return dicTypeMapper.updateDicType(dicType);
    }

    @Override
    public int deleteDicTypeById(String typeId) {
        return dicTypeMapper.deleteDicTypeById(typeId);
    }
}

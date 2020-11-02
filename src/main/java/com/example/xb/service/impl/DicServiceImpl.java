package com.example.xb.service.impl;

import com.example.xb.domain.Dic;
import com.example.xb.mapper.DicMapper;
import com.example.xb.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DicServiceImpl implements DicService {
    @Autowired
    private DicMapper dicMapper;

    @Override
    public List<Dic> dicList(Dic dic) {
        return dicMapper.dicList(dic);
    }

    @Override
    public int saveDic(Dic dic) {
        return dicMapper.saveDic(dic);
    }

    @Override
    public int updateDic(Dic dic) {
        return dicMapper.updateDic(dic);
    }

    @Override
    public int deleteDicById(String dicId) {
        return dicMapper.deleteDicById(dicId);
    }
}

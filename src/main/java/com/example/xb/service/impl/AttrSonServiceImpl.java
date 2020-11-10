package com.example.xb.service.impl;

import com.example.xb.domain.product.AttrSon;
import com.example.xb.mapper.AttrSonMapper;
import com.example.xb.service.AttrSonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttrSonServiceImpl implements AttrSonService {
    @Autowired
    private AttrSonMapper attrSonMapper;

    @Override
    public List<AttrSon> attrSonList(String attrId) {
        return attrSonMapper.attrSonList(attrId);
    }

    @Override
    public int batchSaveAttrSon(List<AttrSon> list) {
        return attrSonMapper.batchSaveAttrSon(list);
    }

    @Override
    public int deleteAttrSonById(String attrId) {
        return attrSonMapper.deleteAttrSonById(attrId);
    }
}

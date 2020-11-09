package com.example.xb.service.impl;

import com.example.xb.domain.AttrBase;
import com.example.xb.domain.AttrSon;
import com.example.xb.mapper.AttrBaseMapper;
import com.example.xb.service.AttrBaseService;
import com.example.xb.service.AttrSonService;
import com.example.xb.utils.JwtUtil;
import com.example.xb.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttrBaseServiceImpl implements AttrBaseService {
    @Autowired
    private AttrBaseMapper attrBaseMapper;
    @Autowired
    private AttrSonService attrSonService;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public List<AttrBase> attrBaseList(String productId) {
        return attrBaseMapper.attrBaseList(productId);
    }

    @Override
    public int batchSaveAttrBase(List<AttrBase> list) {
        for(AttrBase child:list) {
            List<AttrSon> sonList= child.getAttrSonList();
            String attrId = child.getAttrId();
            attrSonService.deleteAttrSonById(attrId);
            if(sonList.size()!=0) {
                for(AttrSon son:sonList) {
                    son.setAttrSonId(UUIDUtil.NewUUID());
                    son.setCreateBy(jwtUtil.getJwtUserId());
                    son.setStatus("0");
                    son.setAttrId(attrId);
                }
                attrSonService.batchSaveAttrSon(sonList);
            }
        }
        return attrBaseMapper.batchSaveAttrBase(list);
    }

    @Override
    public int deleteAttrBaseById(String productId) {
        List<AttrBase> list = attrBaseMapper.attrBaseList(productId);
        for(AttrBase child:list) {
            attrSonService.deleteAttrSonById(child.getAttrId());
        }
        return attrBaseMapper.deleteAttrBaseById(productId);
    }
}

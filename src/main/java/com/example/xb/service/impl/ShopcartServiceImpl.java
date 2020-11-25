package com.example.xb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.xb.domain.shopcart.Shopcart;
import com.example.xb.domain.vo.ShopcartVo;
import com.example.xb.mapper.ShopcartMapper;
import com.example.xb.service.ShopcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopcartServiceImpl extends ServiceImpl<ShopcartMapper, Shopcart> implements ShopcartService {
    @Autowired
    private ShopcartMapper shopcartMapper;

    @Override
    public List<ShopcartVo> getShopcartList(String userId) {
        return shopcartMapper.getShopcartList(userId);
    }

    @Override
    public int batchDelete(List<String> list) {
        return shopcartMapper.batchDelete(list);
    }
}

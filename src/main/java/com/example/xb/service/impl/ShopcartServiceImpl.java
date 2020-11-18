package com.example.xb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.xb.domain.shopcart.Shopcart;
import com.example.xb.mapper.ShopcartMapper;
import com.example.xb.service.ShopcartService;
import org.springframework.stereotype.Service;

@Service
public class ShopcartServiceImpl extends ServiceImpl<ShopcartMapper, Shopcart> implements ShopcartService {

}

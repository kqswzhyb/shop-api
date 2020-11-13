package com.example.xb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.xb.domain.position.City;
import com.example.xb.mapper.SysCitiesMapper;
import com.example.xb.service.SysCitiesService;
import org.springframework.stereotype.Service;

@Service
public class SysCitiesServiceImpl extends ServiceImpl<SysCitiesMapper, City> implements SysCitiesService {
}

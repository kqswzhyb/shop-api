package com.example.xb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.xb.domain.position.Province;
import com.example.xb.mapper.SysProvincesMapper;
import com.example.xb.service.SysProvincesService;
import org.springframework.stereotype.Service;

@Service
public class SysProvincesServiceImpl extends ServiceImpl<SysProvincesMapper, Province> implements SysProvincesService {
}

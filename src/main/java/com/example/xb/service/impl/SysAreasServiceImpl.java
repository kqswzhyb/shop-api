package com.example.xb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.xb.domain.position.Area;
import com.example.xb.mapper.SysAreasMapper;
import com.example.xb.service.SysAreasService;
import org.springframework.stereotype.Service;

@Service
public class SysAreasServiceImpl extends ServiceImpl<SysAreasMapper, Area> implements SysAreasService {
}

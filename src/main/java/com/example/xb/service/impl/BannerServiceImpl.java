package com.example.xb.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.xb.domain.banner.Banner;
import com.example.xb.domain.vo.BannerVo;
import com.example.xb.domain.vo.BrandVo;
import com.example.xb.mapper.BannerMapper;
import com.example.xb.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<BannerVo> bannerList(Banner banner) {
        return bannerMapper.bannerList(banner);
    }

    @Override
    public int saveBanner(BannerVo bannerVo) {
        return bannerMapper.saveBanner(bannerVo);
    }

    @Override
    public int updateBanner(BannerVo bannerVo) {
        return bannerMapper.updateBanner(bannerVo);
    }

    @Override
    public int deleteBannerById(String bannerId) {
        return bannerMapper.deleteBannerById(bannerId);
    }
}

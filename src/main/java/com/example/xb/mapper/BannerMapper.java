package com.example.xb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.xb.domain.banner.Banner;
import com.example.xb.domain.vo.BannerVo;

import java.util.List;

public interface BannerMapper extends BaseMapper<Banner> {
    /**
     * 获取banner列表
     * @param banner
     * @return
     */
    List<BannerVo> bannerList(Banner banner);

    /**
     * 创建新banner
     * @param bannerVo
     * @return
     */
    int saveBanner(BannerVo bannerVo);

    /**
     * 更新banner
     * @param bannerVo
     * @return
     */
    int updateBanner(BannerVo bannerVo);

    /**
     * 通过bannerId删除品牌
     *
     * @param bannerID
     * @return
     */
    int deleteBannerById(String bannerID);
}

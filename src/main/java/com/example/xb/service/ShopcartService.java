package com.example.xb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.xb.domain.shopcart.Shopcart;
import com.example.xb.domain.vo.ShopcartVo;

import java.util.List;

public interface ShopcartService extends IService<Shopcart> {
    /**
     * 获取用户的购物车列表
     * @param userId
     * @return
     */
    List<ShopcartVo> getShopcartList(String userId);
}

package com.example.xb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.xb.domain.shopcart.Shopcart;

import java.util.List;

public interface ShopcartMapper extends BaseMapper<Shopcart> {
    /**
     * 获取用户的购物车列表
     * @param userId
     * @return
     */
    List<Shopcart> getShopcartList(String userId);

    /**
     * 向购物车中添加产品
     * @param shopcart
     * @return
     */
    int addShopcart(Shopcart shopcart);


    /**
     * 从购物车中删除产品
     * @param shopcartId
     * @return
     */
    int deleteShopcart(String shopcartId);

    /**
     * 清空购物车
     * @param userId
     * @return
     */
    int deleteAllShopcart(String userId);
}

package com.example.xb.service;

import com.example.xb.domain.order.Order;
import com.example.xb.domain.vo.OrderVo;

import java.util.List;

public interface OrderService {
    /**
     * 订单列表
     * @param order
     * @return
     */
    List<Order> orderList(Order order);

    /**
     * 创建订单
     * @param orderVo
     * @return
     */
    Boolean saveOrder(OrderVo orderVo);

    /**
     * 更新订单
     * @param orderVo
     * @return
     */
    Boolean updateOrder(OrderVo orderVo);

    /**
     * 更新订单状态
     * @param order
     * @return
     */
    Boolean updateStatus(Order order);
}

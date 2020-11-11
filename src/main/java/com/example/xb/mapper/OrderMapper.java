package com.example.xb.mapper;

import com.example.xb.domain.order.Order;
import com.example.xb.domain.vo.OrderVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
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
    int saveOrder(OrderVo orderVo);

    /**
     * 更新订单
     * @param orderVo
     * @return
     */
    int updateOrder(OrderVo orderVo);

    /**
     * 更新订单状态
     * @param order
     * @return
     */
    int updateStatus(Order order);
}

package com.example.xb.mapper;

import com.example.xb.domain.order.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    /**
     * 订单详情列表
     * @param orderId
     * @return
     */
    List<OrderDetail> orderDetailList(String orderId);

    /**
     * 批量创建订单详情
     * @param list
     * @return
     */
    int batchSaveOrderDetail(List<OrderDetail> list);

    /**
     * 删除订单详情
     * @param orderId
     * @return
     */
    int deleteOrderDetail(String orderId);
}

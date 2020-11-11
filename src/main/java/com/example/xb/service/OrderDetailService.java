package com.example.xb.service;

import com.example.xb.domain.order.OrderDetail;

import java.util.List;

public interface OrderDetailService {
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
    Boolean batchSaveOrderDetail(List<OrderDetail> list);

    /**
     * 删除订单详情
     * @param orderId
     * @return
     */
    Boolean deleteOrderDetail(String orderId);
}

package com.example.xb.service.impl;

import com.example.xb.domain.order.Order;
import com.example.xb.domain.order.OrderDetail;
import com.example.xb.domain.vo.OrderVo;
import com.example.xb.mapper.OrderMapper;
import com.example.xb.service.OrderDetailService;
import com.example.xb.service.OrderService;
import com.example.xb.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    public List<Order> orderList(Order order) {
        return orderMapper.orderList(order);
    }

    @Override
    public Boolean saveOrder(OrderVo orderVo) {
        String orderId = orderVo.getOrderId();
        List<OrderDetail> list =orderVo.getOrderDetailList();
        for(OrderDetail child:list) {
            child.setOrderId(orderId);
            child.setStatus("0");
            child.setOrderDetailId(UUIDUtil.NewUUID());
        }
        return orderMapper.saveOrder(orderVo) == 1 && orderDetailService.batchSaveOrderDetail(list);
    }

    @Override
    public Boolean updateOrder(OrderVo orderVo) {
        String orderId = orderVo.getOrderId();
        List<OrderDetail> list =orderVo.getOrderDetailList();
        for(OrderDetail child:list) {
            child.setOrderId(orderId);
            child.setStatus("0");
            child.setOrderDetailId(UUIDUtil.NewUUID());
        }
        orderDetailService.deleteOrderDetail(orderId);
        return orderMapper.updateOrder(orderVo) == 1 && orderDetailService.batchSaveOrderDetail(list);
    }

    @Override
    public Boolean updateStatus(Order order) {
        return orderMapper.updateStatus(order) == 1;
    }
}

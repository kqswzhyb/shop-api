package com.example.xb.service.impl;

import com.example.xb.domain.order.OrderDetail;
import com.example.xb.mapper.OrderDetailMapper;
import com.example.xb.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public List<OrderDetail> orderDetailList(String orderId) {
        return orderDetailMapper.orderDetailList(orderId);
    }

    @Override
    public Boolean batchSaveOrderDetail(List<OrderDetail> list) {
        return orderDetailMapper.batchSaveOrderDetail(list) != 0;
    }

    @Override
    public Boolean deleteOrderDetail(String orderId) {
        return orderDetailMapper.deleteOrderDetail(orderId) != 0;
    }
}

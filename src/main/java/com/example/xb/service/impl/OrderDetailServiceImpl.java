package com.example.xb.service.impl;

import com.example.xb.domain.order.OrderDetail;
import com.example.xb.domain.product.ProductgStock;
import com.example.xb.mapper.OrderDetailMapper;
import com.example.xb.mapper.ProductgStockMapper;
import com.example.xb.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ProductgStockMapper productgStockMapper;

    @Override
    public List<OrderDetail> orderDetailList(String orderId) {
        return orderDetailMapper.orderDetailList(orderId);
    }

    @Override
    public Boolean batchSaveOrderDetail(List<OrderDetail> list) {
        for(OrderDetail item :list) {
            Integer stock = item.getProductNum();
            ProductgStock productgStock = productgStockMapper.productgStockList(item.getProductgId()).get(0);
            BigDecimal total =productgStock.getTotalStock();
            BigDecimal lock =productgStock.getLockStock();
            productgStock.setLockStock(lock.add(BigDecimal.valueOf((Integer)stock)).compareTo(new BigDecimal("0"))==-1?new BigDecimal("0"):BigDecimal.valueOf((Integer)stock));
            productgStock.setTotalStock(total.subtract(BigDecimal.valueOf((Integer)stock)));
            productgStockMapper.updateStock(productgStock);
        }
        return orderDetailMapper.batchSaveOrderDetail(list) != 0;
    }

    @Override
    public Boolean deleteOrderDetail(String orderId) {
        return orderDetailMapper.deleteOrderDetail(orderId) != 0;
    }
}

package com.example.xb.quartz;

import com.example.xb.domain.order.Order;
import com.example.xb.domain.order.OrderDetail;
import com.example.xb.domain.product.ProductgStock;
import com.example.xb.domain.vo.OrderVo;
import com.example.xb.service.OrderService;
import com.example.xb.service.ProductgStockService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OrderStatusJob extends QuartzJobBean {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductgStockService productgStockService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<OrderVo> list= orderService.orderListRecent();
        List<OrderVo> noList =list.stream().filter(v-> "0".equals(v.getOrderStatus())).collect(Collectors.toList());
        List<OrderVo> partList =list.stream().filter(v-> "3".equals(v.getOrderStatus())).collect(Collectors.toList());
        for (OrderVo child:noList) {
            for(OrderDetail item :child.getOrderDetailList()) {
                Integer stock = item.getProductNum();
               ProductgStock productgStock = productgStockService.productgStockList(item.getProductgId()).get(0);
               BigDecimal total =productgStock.getTotalStock();
               BigDecimal lock =productgStock.getLockStock();
               productgStock.setLockStock(lock.subtract(BigDecimal.valueOf((Integer)stock)).compareTo(new BigDecimal("0"))==-1?new BigDecimal("0"):BigDecimal.valueOf((Integer)stock));
               productgStock.setTotalStock(total.add(BigDecimal.valueOf((Integer)stock)));
               productgStockService.updateStock(productgStock);
            }
            child.setOrderStatus("2");
            orderService.updateStatus(child);
        }
        for (OrderVo child:partList) {
            for(OrderDetail item :child.getOrderDetailList()) {
                Integer stock = item.getProductNum();
                ProductgStock productgStock = productgStockService.productgStockList(item.getProductgId()).get(0);
                BigDecimal total =productgStock.getTotalStock();
                BigDecimal lock =productgStock.getLockStock();
                productgStock.setLockStock(lock.subtract(BigDecimal.valueOf((Integer)stock)).compareTo(new BigDecimal("0"))==-1?new BigDecimal("0"):BigDecimal.valueOf((Integer)stock));
                productgStock.setTotalStock(total.add(BigDecimal.valueOf((Integer)stock)));
                productgStockService.updateStock(productgStock);
            }
            child.setOrderStatus("2");
            orderService.updateStatus(child);
            // TODO 退款
        }
    }

}

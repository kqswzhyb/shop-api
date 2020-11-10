package com.example.xb.domain.vo;

import com.example.xb.domain.order.Order;
import com.example.xb.domain.order.OrderDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderVo  extends Order {
    /**
     * 订单详情列表
     */
    private List<OrderDetail> orderDetailList;
}

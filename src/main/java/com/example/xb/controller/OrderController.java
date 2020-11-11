package com.example.xb.controller;

import com.example.xb.domain.order.Order;
import com.example.xb.domain.page.DataDomain;
import com.example.xb.domain.result.AjaxResult;
import com.example.xb.domain.result.ResultInfo;
import com.example.xb.domain.vo.OrderVo;
import com.example.xb.service.OrderService;
import com.example.xb.utils.JwtUtil;
import com.example.xb.utils.MySnowflake;
import com.example.xb.utils.UUIDUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/v1/order")
@Api(tags = "订单管理")
public class OrderController extends BaseController{
    @Autowired
    private OrderService orderService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取订单列表
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "获取订单列表", notes = "获取订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", defaultValue = "10")
    }
    )
    public AjaxResult list(@ApiIgnore() Order order, String current, String pageSize) {
        DataDomain dd = new DataDomain(current, pageSize);
        ResultInfo resultInfo = startPage(dd);
        List<Order> list = orderService.orderList(order);
        dd.setRecords(list);
        PageInfo pageInfo = new PageInfo(list);
        dd.setTotal(pageInfo.getTotal());

        return new AjaxResult(resultInfo, "1".equals(resultInfo.getCode()) ? null : dd);
    }

    /**
     * 创建新订单
     *
     * @return
     */
    @PostMapping("/save")
    @ApiOperation(value = "创建新订单", notes = "创建新订单")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult save(@RequestBody OrderVo orderVo) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(orderVo.getUserId())) {
            resultInfo.error("userId为空");
            return new AjaxResult(resultInfo, null);
        }
        orderVo.setOrderId(UUIDUtil.NewUUID());
        orderVo.setOrderCode(new MySnowflake(30, 30).getUUID()+"");
        orderVo.setCreateBy(jwtUtil.getJwtUserId());
        orderVo.setOrderStatus("0");
        if (orderService.saveOrder(orderVo)) {
            resultInfo.success("创建成功");
        } else {
            resultInfo.error("创建失败");
        }
        return new AjaxResult(resultInfo, null);
    }

    /**
     * 更新订单
     *
     * @return
     */
    @PutMapping("/updateOrder")
    @ApiOperation(value = "更新订单", notes = "更新订单")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateOrder(@RequestBody OrderVo orderVo) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(orderVo.getOrderId())) {
            resultInfo.error("orderId为空");
            return new AjaxResult(resultInfo, null);
        }
        if (orderService.updateOrder(orderVo)) {
            resultInfo.success("更新成功");
        } else {
            resultInfo.error("更新失败");
        }
        return new AjaxResult(resultInfo, null);
    }

    /**
     * 更新订单状态
     *
     * @return
     */
    @PutMapping("/updateStatus")
    @ApiOperation(value = "更新订单状态", notes = "更新订单状态")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateStatus(@RequestBody OrderVo orderVo) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(orderVo.getOrderId())) {
            resultInfo.error("orderId为空");
            return new AjaxResult(resultInfo, null);
        }
        if (orderService.updateOrder(orderVo)) {
            resultInfo.success("更新成功");
        } else {
            resultInfo.error("更新失败");
        }
        return new AjaxResult(resultInfo, null);
    }
}

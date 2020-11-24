package com.example.xb.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.xb.domain.result.AjaxResult;
import com.example.xb.domain.result.ResultInfo;
import com.example.xb.domain.shopcart.Shopcart;
import com.example.xb.service.ShopcartService;
import com.example.xb.utils.DateUtil;
import com.example.xb.utils.JwtUtil;
import com.example.xb.utils.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/v1/shopcart")
@Api(tags = "购物车")
public class ShopcartController {
    @Autowired
    private ShopcartService shopcartService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private DateUtil dateUtil;

    /**
     * 获取用户购物车
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "获取用户购物车", notes = "获取用户购物车")
    public AjaxResult shopcartList(HttpServletRequest req) {
        return new AjaxResult(new ResultInfo(), shopcartService.getShopcartList(jwtUtil.getUserId(req)));
    }

    /**
     * 添加购物车
     *
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加购物车", notes = "添加购物车")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult addShopcart(@RequestBody Shopcart shopcart, HttpServletRequest req) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(shopcart.getProductgId())) {
            resultInfo.error("产品为空");
            return new AjaxResult(resultInfo, null);
        }
        Shopcart queryResult= shopcartService.getOne(new LambdaQueryWrapper<Shopcart>()
                .eq(Shopcart::getUserId, jwtUtil.getUserId(req))
                .eq(Shopcart::getProductgId, shopcart.getProductgId()));
        if(queryResult!=null) {
            queryResult.setCartNumber(shopcart.getCartNumber().add(queryResult.getCartNumber()));
            UpdateWrapper<Shopcart> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("shopcart_id",queryResult.getShopcartId());
            updateWrapper.set("cart_number",queryResult.getCartNumber());
            updateWrapper.set("update_at",dateUtil.localToDate(LocalDateTime.now()));
            shopcartService.update(null,updateWrapper);
        }else {
            shopcart.setShopcartId(UUIDUtil.NewUUID());
            shopcart.setStatus("0");
            shopcart.setCreateBy(jwtUtil.getUserId(req));
            shopcart.setCreateAt(dateUtil.localToDate(LocalDateTime.now()));
            shopcart.setUpdateAt(dateUtil.localToDate(LocalDateTime.now()));
            shopcart.setUserId(jwtUtil.getUserId(req));
            shopcartService.save(shopcart);
        }

        return new AjaxResult(resultInfo, null);
    }

    /**
     * 更新购物车
     *
     * @return
     */
    @PutMapping("/update")
    @ApiOperation(value = "更新购物车", notes = "更新购物车")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateShopcart(@RequestBody Shopcart shopcart) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(shopcart.getShopcartId())) {
            resultInfo.error("id为空");
            return new AjaxResult(resultInfo, null);
        }
        UpdateWrapper<Shopcart> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("shopcart_id",shopcart.getShopcartId());
        updateWrapper.set("cart_number",shopcart.getCartNumber());
        updateWrapper.set("update_at",dateUtil.localToDate(LocalDateTime.now()));
        shopcartService.update(null,updateWrapper);

        return new AjaxResult(resultInfo, null);
    }

    /**
     * 根据shopcarId移除购物车
     *
     * @return
     */
    @DeleteMapping("/deleteByCar")
    @ApiOperation(value = "根据shopcarId移除购物车", notes = "根据shopcarId移除购物车")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult deleteShopcartByCart(String shopcartId) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(shopcartId)) {
            resultInfo.error("shopcartId为空");
            return new AjaxResult(resultInfo, null);
        }

        return new AjaxResult(resultInfo, shopcartService.remove(new LambdaQueryWrapper<Shopcart>()
                .eq(Shopcart::getShopcartId, shopcartId)));
    }

    /**
     * 根据userId清空购物车
     *
     * @return
     */
    @DeleteMapping("/deleteByUser")
    @ApiOperation(value = "根据userId清空购物车", notes = "根据userId清空购物车")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult deleteShopcartByUser(String userId) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(userId)) {
            resultInfo.error("userId为空");
            return new AjaxResult(resultInfo, null);
        }
        return new AjaxResult(resultInfo, shopcartService.remove(new LambdaQueryWrapper<Shopcart>()
                .eq(Shopcart::getUserId, userId)));
    }
}

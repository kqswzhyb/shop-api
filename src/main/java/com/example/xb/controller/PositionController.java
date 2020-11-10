package com.example.xb.controller;

import com.example.xb.domain.result.AjaxResult;
import com.example.xb.domain.result.ResultInfo;
import com.example.xb.service.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/position")
@Api(tags = "省市区")
public class PositionController {
    @Autowired
    private PositionService positionService;

    /**
     * 获取省列表
     *
     * @return
     */
    @GetMapping("/province/{countryId}")
    @ApiOperation(value = "获取省列表", notes = "获取省列表")
    public AjaxResult provinceList(@PathVariable("countryId") String countryId) {
        return new AjaxResult(new ResultInfo(), positionService.provinceList(countryId));
    }

    /**
     * 获取市列表
     *
     * @return
     */
    @GetMapping("/city/{provinceId}")
    @ApiOperation(value = "获取市列表", notes = "获取市列表")
    public AjaxResult cityList(@PathVariable("provinceId") String provinceId) {
        return new AjaxResult(new ResultInfo(), positionService.cityList(provinceId));
    }

    /**
     * 获取区列表
     *
     * @return
     */
    @GetMapping("/area/{cityId}")
    @ApiOperation(value = "获取区列表", notes = "获取区列表")
    public AjaxResult areaList(@PathVariable("cityId") String cityId) {
        return new AjaxResult(new ResultInfo(), positionService.areaList(cityId));
    }
}

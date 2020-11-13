package com.example.xb.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.xb.domain.position.Area;
import com.example.xb.domain.position.City;
import com.example.xb.domain.position.Province;
import com.example.xb.domain.result.AjaxResult;
import com.example.xb.domain.result.ResultInfo;
import com.example.xb.service.SysAreasService;
import com.example.xb.service.SysCitiesService;
import com.example.xb.service.SysProvincesService;
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
    private SysProvincesService sysProvincesService;

    @Autowired
    private SysCitiesService sysCitiesService;

    @Autowired
    private SysAreasService sysAreasService;

    /**
     * 获取省列表
     *
     * @return
     */
    @GetMapping("/province/{countryId}")
    @ApiOperation(value = "获取省列表", notes = "获取省列表")
    public AjaxResult provinceList(@PathVariable("countryId") String countryId) {
//        QueryWrapper<Province> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("country_id", countryId);
        return new AjaxResult(new ResultInfo(), sysProvincesService.list(new LambdaQueryWrapper<Province>()
                .eq(Province::getCountryId, countryId)));
    }

    /**
     * 获取市列表
     *
     * @return
     */
    @GetMapping("/city/{provinceId}")
    @ApiOperation(value = "获取市列表", notes = "获取市列表")
    public AjaxResult cityList(@PathVariable("provinceId") String provinceId) {
        return new AjaxResult(new ResultInfo(), sysCitiesService.list(new LambdaQueryWrapper<City>()
                .eq(City::getProvinceId, provinceId)));
    }

    /**
     * 获取区列表
     *
     * @return
     */
    @GetMapping("/area/{cityId}")
    @ApiOperation(value = "获取区列表", notes = "获取区列表")
    public AjaxResult areaList(@PathVariable("cityId") String cityId) {
        return new AjaxResult(new ResultInfo(), sysAreasService.list(new LambdaQueryWrapper<Area>()
                .eq(Area::getCityId, cityId)));
    }
}

package com.example.xb.controller;

import com.example.xb.domain.Dic;
import com.example.xb.domain.DicType;
import com.example.xb.domain.Role;
import com.example.xb.domain.page.DataDomain;
import com.example.xb.domain.result.AjaxResult;
import com.example.xb.domain.result.ResultInfo;
import com.example.xb.service.DicService;
import com.example.xb.service.DicTypeService;
import com.example.xb.utils.JwtUtil;
import com.example.xb.utils.UUIDUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/v1/dic")
@Api(tags = "字典管理")
public class DicController extends  BaseController{

    @Autowired
    private DicTypeService dicTypeService;

    @Autowired
    private DicService dicService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/typeList")
    @ApiOperation(value = "分页获取字典类型列表", notes = "分页获取字典类型列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页数量", defaultValue = "10")
    }
    )
    public AjaxResult listType(@ApiIgnore()DicType dicType, String current, String size) {
        DataDomain dd = new DataDomain();
        dd.setCurrent(!StringUtils.isEmptyOrWhitespace(current) ? current : "1");
        dd.setSize(!StringUtils.isEmptyOrWhitespace(size) ? size : "10");
        ResultInfo resultInfo = startPage(dd);
        List<DicType> list = dicTypeService.dicTypeList(dicType);
        dd.setRecords(list);
        PageInfo pageInfo = new PageInfo(list);
        dd.setTotal(pageInfo.getTotal());
        return new AjaxResult(resultInfo, "1".equals(resultInfo.getCode()) ? null : dd);
    }

    /**
     * 创建新字典类型
     *
     * @return
     */
    @PostMapping("/saveType")
    @ApiOperation(value = "创建新字典类型", notes = "创建新字典类型")
    public AjaxResult saveType(@RequestBody DicType dicType) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(dicType.getName())) {
            resultInfo.error("字典类型名称为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(dicType.getCode())) {
            resultInfo.error("字典类型编码为空");
            return new AjaxResult(resultInfo, null);
        }
        dicType.setTypeId(UUIDUtil.NewUUID());
        dicType.setCreateBy(jwtUtil.getJwtUserId());
        dicType.setStatus("0");
        int i = dicTypeService.saveDicType(dicType);
        if (i == 1) {
            resultInfo.success("创建成功");
        } else {
            resultInfo.error("创建失败");
        }
        return new AjaxResult(resultInfo, null);
    }

    /**
     * 更新字典类型
     *
     * @return
     */
    @PutMapping("/updateDicType")
    @ApiOperation(value = "更新字典类型", notes = "更新字典类型")
    public AjaxResult updateType(@RequestBody DicType dicType) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(dicType.getTypeId())) {
            resultInfo.error("typeId为空");
            return new AjaxResult(resultInfo, null);
        }
        int i = dicTypeService.updateDicType(dicType);
        if (i == 1) {
            resultInfo.success("更新成功");
        } else {
            resultInfo.error("更新失败");
        }
        return new AjaxResult(resultInfo, null);
    }

    /**
     * 根据id删除字典类型
     *
     * @return
     */
    @DeleteMapping("/deleteDicType")
    @ApiOperation(value = "根据id删除字典类型", notes = "根据id删除字典类型")
    public AjaxResult deleteType(String typeID) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(typeID)) {
            resultInfo.error("typeId不能为空");
            return new AjaxResult(resultInfo, null);
        }
        int i = dicTypeService.deleteDicTypeById(typeID);
        if (i == 1) {
            resultInfo.success("删除成功");
        } else {
            resultInfo.error("该typeId不存在，无法删除");
        }
        return new AjaxResult(resultInfo, null);
    }

    @GetMapping("/list")
    @ApiOperation(value = "分页获取字典列表", notes = "分页获取字典列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页数量", defaultValue = "10")
    }
    )
    public AjaxResult list(@ApiIgnore() Dic dic, String current, String size) {
        DataDomain dd = new DataDomain();
        dd.setCurrent(!StringUtils.isEmptyOrWhitespace(current) ? current : "1");
        dd.setSize(!StringUtils.isEmptyOrWhitespace(size) ? size : "10");
        ResultInfo resultInfo = startPage(dd);
        List<Dic> list = dicService.dicList(dic);
        dd.setRecords(list);
        PageInfo pageInfo = new PageInfo(list);
        dd.setTotal(pageInfo.getTotal());
        return new AjaxResult(resultInfo, "1".equals(resultInfo.getCode()) ? null : dd);
    }

    /**
     * 创建新字典
     *
     * @return
     */
    @PostMapping("/save")
    @ApiOperation(value = "创建新字典", notes = "创建新字典")
    public AjaxResult save(@RequestBody Dic dic) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(dic.getTypeId())) {
            resultInfo.error("typeId为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(dic.getName())) {
            resultInfo.error("字典名称为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(dic.getValue())) {
            resultInfo.error("字典值为空");
            return new AjaxResult(resultInfo, null);
        }
        dic.setDicId(UUIDUtil.NewUUID());
        dic.setCreateBy(jwtUtil.getJwtUserId());
        dic.setStatus("0");
        int i = dicService.saveDic(dic);
        if (i == 1) {
            resultInfo.success("创建成功");
        } else {
            resultInfo.error("创建失败");
        }
        return new AjaxResult(resultInfo, null);
    }

    /**
     * 更新字典
     *
     * @return
     */
    @PutMapping("/updateDic")
    @ApiOperation(value = "更新字典", notes = "更新字典")
    public AjaxResult update(@RequestBody Dic dic) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(dic.getDicId())) {
            resultInfo.error("dicId为空");
            return new AjaxResult(resultInfo, null);
        }
        int i = dicService.updateDic(dic);
        if (i == 1) {
            resultInfo.success("更新成功");
        } else {
            resultInfo.error("更新失败");
        }
        return new AjaxResult(resultInfo, null);
    }

    /**
     * 根据id删除字典
     *
     * @return
     */
    @DeleteMapping("/deleteDic")
    @ApiOperation(value = "根据id删除字典", notes = "根据id删除字典")
    public AjaxResult delete(String dicId) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(dicId)) {
            resultInfo.error("dicId不能为空");
            return new AjaxResult(resultInfo, null);
        }
        int i = dicTypeService.deleteDicTypeById(dicId);
        if (i == 1) {
            resultInfo.success("删除成功");
        } else {
            resultInfo.error("该typeId不存在，无法删除");
        }
        return new AjaxResult(resultInfo, null);
    }
}

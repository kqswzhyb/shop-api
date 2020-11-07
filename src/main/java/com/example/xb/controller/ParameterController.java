package com.example.xb.controller;

import com.example.xb.domain.Parameter;
import com.example.xb.domain.Role;
import com.example.xb.domain.page.DataDomain;
import com.example.xb.domain.result.AjaxResult;
import com.example.xb.domain.result.ResultInfo;
import com.example.xb.service.ParameterService;
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
@RequestMapping("/v1/parameter")
@Api(tags = "产品参数管理")
public class ParameterController extends BaseController{
    @Autowired
    private ParameterService parameterService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取产品参数列表
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "获取产品参数列表", notes = "获取产品参数列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", defaultValue = "10")
    }
    )
    public AjaxResult list(@ApiIgnore() Parameter parameter, String current, String pageSize) {
        DataDomain dd = new DataDomain(current, pageSize);
        ResultInfo resultInfo = startPage(dd);
        List<Parameter> list = parameterService.parameterList(parameter);
        dd.setRecords(list);
        PageInfo pageInfo = new PageInfo(list);
        dd.setTotal(pageInfo.getTotal());

        return new AjaxResult(resultInfo, "1".equals(resultInfo.getCode()) ? null : dd);
    }

    /**
     * 创建新参数
     *
     * @return
     */
    @PostMapping("/save")
    @ApiOperation(value = "创建新参数", notes = "创建新参数")
    public AjaxResult save(@RequestBody Parameter parameter) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(parameter.getName())) {
            resultInfo.error("参数名为空");
            return new AjaxResult(resultInfo, null);
        }
        parameter.setParameterId(UUIDUtil.NewUUID());
        parameter.setCreateBy(jwtUtil.getJwtUserId());
        parameter.setStatus("0");
        int i = parameterService.saveParameter(parameter);
        if (i == 1) {
            resultInfo.success("创建成功");
        } else {
            resultInfo.error("创建失败");
        }
        return new AjaxResult(resultInfo, null);
    }

    /**
     * 更新参数
     *
     * @return
     */
    @PutMapping("/update")
    @ApiOperation(value = "更新参数", notes = "更新参数")
    public AjaxResult update(@RequestBody Parameter parameter) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(parameter.getParameterId())) {
            resultInfo.error("parameterId为空");
            return new AjaxResult(resultInfo, null);
        }
        int i = parameterService.updateParameter(parameter);
        if (i == 1) {
            resultInfo.success("更新成功");
        } else {
            resultInfo.error("更新失败");
        }
        return new AjaxResult(resultInfo, null);
    }

    /**
     * 根据id删除参数
     *
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "根据id删除参数", notes = "根据id删除参数")
    public AjaxResult delete(String parameterId) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(parameterId)) {
            resultInfo.error("parameterId不能为空");
            return new AjaxResult(resultInfo, null);
        }
        int i = parameterService.deleteParameterById(parameterId);
        if (i == 1) {
            resultInfo.success("删除成功");
        } else {
            resultInfo.error("该roleId不存在，无法删除");
        }
        return new AjaxResult(resultInfo, null);
    }
}

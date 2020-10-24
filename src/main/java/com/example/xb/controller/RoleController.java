package com.example.xb.controller;


import com.example.xb.domain.Role;
import com.example.xb.domain.User;
import com.example.xb.domain.page.DataDomain;
import com.example.xb.domain.result.AjaxResult;
import com.example.xb.domain.result.ResultInfo;
import com.example.xb.service.RoleService;
import com.example.xb.utils.AESUtil;
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

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/v1/role")
@Api(tags = "角色管理")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
     * 获取角色列表
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "分页获取角色信息", notes = "分页获取角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页数量", defaultValue = "10")
    }
    )
    public AjaxResult list(@ApiIgnore() Role role, String current, String size) {
        DataDomain dd = new DataDomain();
        dd.setCurrent(!StringUtils.isEmptyOrWhitespace(current) ? current : "1");
        dd.setSize(!StringUtils.isEmptyOrWhitespace(size) ? size : "10");
        ResultInfo resultInfo = startPage(dd);
        List<Role> list = roleService.roleList(role);
        dd.setRecords(list);
        PageInfo pageInfo = new PageInfo(list);
        dd.setTotal(pageInfo.getTotal());

        return new AjaxResult(resultInfo, "1".equals(resultInfo.getCode()) ? null : dd);
    }

    /**
     * 创建新角色
     *
     * @return
     */
    @PostMapping("/save")
    @ApiOperation(value = "创建新角色", notes = "创建新角色")
    public AjaxResult save(@RequestBody Role role) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(role.getRoleName())) {
            resultInfo.error("角色名为空");
            return new AjaxResult(resultInfo, null);
        }
        role.setRoleId(UUIDUtil.NewUUID());
        role.setStatus("0");
        int i = roleService.saveRole(role);
        if (i == 1) {
            resultInfo.success("创建成功");
        } else {
            resultInfo.error("创建失败");
        }
        return new AjaxResult(resultInfo, null);
    }

    /**
     * 更新用户
     *
     * @return
     */
    @PutMapping("/update")
    @ApiOperation(value = "更新角色", notes = "更新角色")
    public AjaxResult update(@RequestBody Role role) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(role.getRoleId())) {
            resultInfo.error("roleId为空");
            return new AjaxResult(resultInfo, null);
        }
        int i = roleService.updateRole(role);
        if (i == 1) {
            resultInfo.success("更新成功");
        } else {
            resultInfo.error("更新失败");
        }
        return new AjaxResult(resultInfo, null);
    }

    /**
     * 根据id删除用户
     *
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "根据id删除用户", notes = "根据id删除用户")
    public AjaxResult delete(String roleId) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(roleId)) {
            resultInfo.error("roleId不能为空");
            return new AjaxResult(resultInfo, null);
        }
        int i = roleService.deleteRoleById(roleId);
        if (i == 1) {
            resultInfo.success("删除成功");
        } else {
            resultInfo.error("该roleId不存在，无法删除");
        }
        return new AjaxResult(resultInfo, null);
    }
}

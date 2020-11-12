package com.example.xb.controller;


import com.example.xb.domain.role.Role;
import com.example.xb.domain.role.RoleMenu;
import com.example.xb.domain.page.DataDomain;
import com.example.xb.domain.result.AjaxResult;
import com.example.xb.domain.result.ResultInfo;
import com.example.xb.domain.role.roleMenuBody;
import com.example.xb.service.RoleMenuService;
import com.example.xb.service.RoleService;
import com.example.xb.utils.JwtUtil;
import com.example.xb.utils.UUIDUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
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

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取角色列表
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "分页获取角色信息", notes = "分页获取角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", defaultValue = "10")
    }
    )
    public AjaxResult list(@ApiIgnore() Role role, String current, String pageSize) {
        DataDomain dd = new DataDomain(current, pageSize);
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
        role.setCreateBy(jwtUtil.getJwtUserId());
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
     * 更新角色
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
     * 根据id删除角色
     *
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "根据id删除角色", notes = "根据id删除角色")
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

    /**
     * 通过roleID获取角色的菜单权限列表
     *
     * @return
     */
    @GetMapping("/roleMenuByRoleId")
    @ApiOperation(value = "通过roleID获取角色的菜单权限列表", notes = "通过roleID获取角色的菜单权限列表")
    public AjaxResult roleMenu(String roleId) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.success("操作成功");
        return new AjaxResult(resultInfo, roleMenuService.queryRoleMenuList(roleId));
    }

    /**
     * 通过roleID获取角色的所有权限列表
     *
     * @return
     */
    @GetMapping("/roleAllMenuByRoleId")
    @ApiOperation(value = "通过roleID获取角色的所有权限列表", notes = "通过roleID获取角色的所有权限列表")
    public AjaxResult roleMenuAll(String roleId) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.success("操作成功");
        return new AjaxResult(resultInfo, roleMenuService.queryRoleMenuAllList(roleId));
    }

    /**
     * 通过roleID批量修改权限列表
     *
     * @return
     */
    @PostMapping("/batchSaveRoleMenu")
    @ApiOperation(value = "通过roleID批量修改权限列表", notes = "通过roleID批量修改权限列表")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult batchSaveRoleMenu(@RequestBody roleMenuBody roleMenuBody) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            if (StringUtils.isEmptyOrWhitespace(roleMenuBody.getRoleId())) {
                resultInfo.error("roleId不能为空");
                return new AjaxResult(resultInfo, null);
            }
            roleMenuService.deleteRoleById(roleMenuBody.getRoleId());
            String[] list= roleMenuBody.getMenus().split(",");
            List<RoleMenu> roleMenus=new ArrayList<>();
            for(String child:list) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleMenuBody.getRoleId());
                roleMenu.setMenuId(child);
                roleMenus.add(roleMenu);
            }
            if(!StringUtils.isEmptyOrWhitespace(roleMenuBody.getMenus())&&list.length!=0) {
                roleMenuService.batchSave(roleMenus);
            }
            resultInfo.success("修改成功");
        }catch (Exception e) {
            resultInfo.error("操作失败");
        }
        return new AjaxResult(resultInfo, null);
    }
}

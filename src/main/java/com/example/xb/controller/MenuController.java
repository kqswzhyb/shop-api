package com.example.xb.controller;

import com.example.xb.domain.Menu;
import com.example.xb.domain.page.DataDomain;
import com.example.xb.domain.result.AjaxResult;
import com.example.xb.domain.result.ResultInfo;
import com.example.xb.domain.vo.MenuVo;
import com.example.xb.service.MenuService;
import com.example.xb.utils.JwtUtil;
import com.example.xb.utils.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/v1/menu")
@Api(tags = "权限管理")
public class MenuController extends  BaseController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取角色列表
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "获取权限列表", notes = "获取权限列表")
    public AjaxResult list() {
        DataDomain dd = new DataDomain();
        dd.setCurrent("1");
        dd.setSize(Integer.MAX_VALUE+"");
        ResultInfo resultInfo = startPage(dd);
        List<MenuVo> list = menuService.queryMenuList();;
        return new AjaxResult(resultInfo, list);
    }

    /**
     * 创建新权限
     *
     * @return
     */
    @PostMapping("/save")
    @ApiOperation(value = "创建新权限", notes = "创建新权限")
    public AjaxResult save(@RequestBody Menu menu) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(menu.getName())) {
            resultInfo.error("权限名称为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(menu.getType())) {
            resultInfo.error("类型为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(menu.getParentId())) {
            resultInfo.error("父级id为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(menu.getPermission())) {
            resultInfo.error("权限变量名为空");
            return new AjaxResult(resultInfo, null);
        }
        menu.setMenuId(UUIDUtil.NewUUID());
        menu.setCreateBy(jwtUtil.getJwtUserId());
        menu.setStatus("0");
        int i = menuService.saveMenu(menu);
        if (i == 1) {
            resultInfo.success("创建成功");
        } else {
            resultInfo.error("创建失败");
        }
        return new AjaxResult(resultInfo, null);
    }

    /**
     * 更新权限
     *
     * @return
     */
    @PutMapping("/update")
    @ApiOperation(value = "更新权限", notes = "更新权限")
    public AjaxResult update(@RequestBody Menu menu) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(menu.getName())) {
            resultInfo.error("权限名称为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(menu.getMenuId())) {
            resultInfo.error("权限id为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(menu.getType())) {
            resultInfo.error("类型为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(menu.getParentId())) {
            resultInfo.error("父级id为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(menu.getPermission())) {
            resultInfo.error("权限变量名为空");
            return new AjaxResult(resultInfo, null);
        }
        int i = menuService.updateMenu(menu);
        if (i == 1) {
            resultInfo.success("更新成功");
        } else {
            resultInfo.error("更新失败");
        }
        return new AjaxResult(resultInfo, null);
    }

    /**
     * 根据id删除权限
     *
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "根据id删除权限", notes = "根据id删除权限")
    public AjaxResult deleteMenuById(String menuId) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(menuId)) {
            resultInfo.error("menuId不能为空");
            return new AjaxResult(resultInfo, null);
        }
        int i = menuService.deleteMenuById(menuId);
        if (i == 1) {
            resultInfo.success("删除成功");
        } else {
            resultInfo.error("该menuId不存在，无法删除");
        }
        return new AjaxResult(resultInfo, null);
    }
}

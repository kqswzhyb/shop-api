package com.example.xb.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.example.xb.domain.User;
import com.example.xb.domain.page.DataDomain;
import com.example.xb.domain.result.AjaxResult;
import com.example.xb.domain.result.ResultInfo;
import com.example.xb.domain.Password;
import com.example.xb.domain.vo.MenuVo;
import com.example.xb.service.RoleMenuService;
import com.example.xb.service.UserService;
import com.example.xb.utils.*;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/v1/user")
@Api(tags = "用户管理")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Value("${token.password.secret}")
    private String SECRET_KEY;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取用户列表
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "分页获取用户信息", notes = "分页获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", defaultValue = "10")
    }
    )
    public AjaxResult list(@ApiIgnore() User user, String current, String pageSize) {
        DataDomain dd = new DataDomain(current, pageSize);
        ResultInfo resultInfo = startPage(dd);
        List<User> list = userService.selectUserList(user);
        dd.setRecords(list);
        PageInfo pageInfo = new PageInfo(list);
        dd.setTotal(pageInfo.getTotal());

        return new AjaxResult(resultInfo, resultInfo.getCode().equals("1") ? null : dd);
    }

    /**
     * 获取用户excel
     *
     * @return
     */
    @GetMapping("/userExcel")
    @ApiOperation(value = "获取用户Excel", notes = "获取Excel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", defaultValue = "10")
    }
    )
    public void excel(String current, String pageSize, HttpServletResponse response) throws IOException {

        DataDomain dd = new DataDomain(current, pageSize);
        startPage(dd);
        List<User> list =userService.selectUserList(new User());
        for(User child:list) {
            child.setStatus(child.getStatus().equals("0")?"正常":"禁用");
        }
        String fileName = "用户列表.xlsx";
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", " attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(response.getOutputStream(),User.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(list, writeSheet);

        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }

    /**
     * 创建新用户
     *
     * @return
     */
    @PostMapping("/save")
    @ApiOperation(value = "创建新用户", notes = "创建新用户")
    public AjaxResult save(@RequestBody User user) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(user.getUserName())) {
            resultInfo.error("用户名为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(user.getPassword())) {
            resultInfo.error("密码为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(user.getNickName())) {
            user.setNickName("用户" + UUIDUtil.NewLowUUID().substring(0, 10));
        }
        int count = userService.queryCountByName(user.getUserName());
        if (count == 0) {
            user.setPassword(AESUtil.encryptIntoHexString(user.getPassword(), SECRET_KEY));
            user.setUserId(UUIDUtil.NewUUID());
            user.setCreateBy(jwtUtil.getJwtUserId());
            user.setStatus("0");

            int i = userService.saveUser(user);
            if (i == 1) {
                resultInfo.success("创建成功");
            } else {
                resultInfo.error("创建失败");
            }
        } else {
            resultInfo.error("该用户名重复");
        }
        return new AjaxResult(resultInfo, null);
    }

    /**
     * 更新用户
     *
     * @return
     */
    @PutMapping("/update")
    @ApiOperation(value = "更新用户", notes = "更新用户")
    public AjaxResult update(@RequestBody User user) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(user.getUserId())) {
            resultInfo.error("userId为空");
            return new AjaxResult(resultInfo, null);
        }
        int count = userService.queryCountById(user.getUserId());
        if (count == 0) {
            resultInfo.error("用户名不存在");
        } else {
            int i = userService.updateUser(user);
            if (i == 1) {
                resultInfo.success("更新成功");
            } else {
                resultInfo.error("更新失败");
            }
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
    public AjaxResult delete(String userId) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(userId)) {
            resultInfo.error("userId不能为空");
            return new AjaxResult(resultInfo, null);
        }
        int i = userService.deleteUserById(userId);
        if (i == 1) {
            resultInfo.success("删除成功");
        } else {
            resultInfo.error("该userId不存在，无法删除");
        }
        return new AjaxResult(resultInfo, null);
    }

    /**
     * 更新密码
     *
     * @return
     */
    @PutMapping("/updatePassword")
    @ApiOperation(value = "更新密码", notes = "更新密码")
    public AjaxResult updatePassword(@RequestBody Password password) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(password.getUserId())) {
            resultInfo.error("userId为空");
            return new AjaxResult(resultInfo, null);
        }

        if (StringUtils.isEmptyOrWhitespace(password.getOldPassword())) {
            resultInfo.error("原始密码为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(password.getNewPassword())) {
            resultInfo.error("新密码为空");
            return new AjaxResult(resultInfo, null);
        }
        int count = userService.queryCountById(password.getUserId());
        if (count == 0) {
            resultInfo.error("用户名不存在");
        } else {
            int i = userService.updatePassword(password);
            if (i == 1) {
                resultInfo.success("更新成功");
            } else {
                resultInfo.error("更新失败");
            }
        }
        return new AjaxResult(resultInfo, null);
    }

    /**
     * 通过用户名查询个数
     *
     * @return
     */
    @GetMapping("/queryCountByName")
    @ApiOperation(value = "通过用户名查询个数", notes = "通过用户名查询个数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true),
    }
    )
    public AjaxResult queryCountByName(String userName) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(userName)) {
            resultInfo.error("用户名为空");
            return new AjaxResult(resultInfo, null);
        }
        int count = userService.queryCountByName(userName);
        if (count == 0) {
            resultInfo.success("该用户名可用");
        } else {
            resultInfo.error("该用户名重复");
        }

        return new AjaxResult(resultInfo, null);
    }

    /**
     * 获取登录用户详情
     *
     * @return
     */
    @GetMapping("/info")
    @ApiOperation(value = "获取登录用户详情", notes = "获取登录用户详情")
    public AjaxResult info() {
        User user= new User();
        user.setUserId(jwtUtil.getJwtUserId());
        User loginUser = userService.selectUserList(user).get(0);
        List<MenuVo> menus= roleMenuService.queryRoleMenuList(loginUser.getRoleId());
        List<String> permission = roleMenuService.queryPermissionList(loginUser.getRoleId());
        HashMap<String,Object> map= new HashMap<>();
        map.put("info",loginUser);
        map.put("menus",menus);
        map.put("permission",permission);
        return new AjaxResult(new ResultInfo(), map);
    }


}

package com.example.xb.controller;

import com.example.xb.domain.LoginUser;
import com.example.xb.domain.User;
import com.example.xb.domain.page.DataDomain;
import com.example.xb.domain.result.AjaxResult;
import com.example.xb.domain.result.ResultInfo;
import com.example.xb.service.IUserService;
import com.example.xb.utils.JwtUtil;
import com.example.xb.utils.RedisCache;
import com.example.xb.utils.TokenUtil;
import com.example.xb.utils.UUIDUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/v1/user")
@Api(tags = "用户管理")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private RedisCache redisCache;

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
            @ApiImplicitParam(name = "size", value = "每页数量", defaultValue = "10")
    }
    )
    public AjaxResult list(@ApiIgnore() User user, String current, String size) {
        DataDomain dd = new DataDomain();
        dd.setCurrent(!StringUtils.isEmptyOrWhitespace(current)?current:"1");
        dd.setSize(!StringUtils.isEmptyOrWhitespace(size)?size:"10");
        ResultInfo resultInfo = startPage(dd);
        List<User> list = userService.selectUserList(user);
        dd.setRecords(list);
        PageInfo pageInfo = new PageInfo(list);
        dd.setTotal(pageInfo.getTotal());

        User user2 = new User();
        user2.setUserId("f9149b3a247b4106af112227e1ade9fb");
        user2.setNickName("123");
        user2.setUserName("456");
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(user2);
        String token2 = tokenUtil.createToken(loginUser);
        System.out.println(token2);
        Map<String, Object> map3 = jwtUtil.parseToken(token2);
        System.out.println(map3);


        Object obj = redisCache.getCacheObject("f9149b3a247b4106af112227e1ade9fb");
        System.out.println(obj);

        return new AjaxResult(resultInfo, resultInfo.getCode().equals("1") ? null : dd);
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
        if(StringUtils.isEmptyOrWhitespace(user.getUserName())) {
            resultInfo.error("用户名为空");
            return new AjaxResult(resultInfo, null);
        }
        if(StringUtils.isEmptyOrWhitespace(user.getPassword())) {
            resultInfo.error("密码为空");
            return new AjaxResult(resultInfo, null);
        }
        if(StringUtils.isEmptyOrWhitespace(user.getNickName())) {
            user.setNickName("用户"+UUIDUtil.NewLowUUID().substring(0,10));
        }
        user.setUserId(UUIDUtil.NewUUID());
        user.setStatus("0");
        user.setRoleId("1");

        int i = userService.saveUser(user);
        if(i==1) {
            resultInfo.success("创建成功");
        }else {
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
    @ApiOperation(value = "更新用户", notes = "更新用户")
    public AjaxResult update(@RequestBody User user) {
        ResultInfo resultInfo = new ResultInfo();
        if(StringUtils.isEmptyOrWhitespace(user.getUserId())) {
            resultInfo.error("userId为空");
            return new AjaxResult(resultInfo, null);
        }

        int i = userService.updateUser(user);
        if(i==1) {
            resultInfo.success("更新成功");
        }else {
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
    public AjaxResult save(String userId) {
        ResultInfo resultInfo = new ResultInfo();
        if(StringUtils.isEmptyOrWhitespace(userId)) {
            resultInfo.error("userId不能为空");
            return new AjaxResult(resultInfo, null);
        }
        int i = userService.deleteUserById(userId);
        if(i==1) {
            resultInfo.success("删除成功");
        }else {
            resultInfo.error("该userId不存在，无法删除");
        }
        return new AjaxResult(resultInfo, null);
    }
}

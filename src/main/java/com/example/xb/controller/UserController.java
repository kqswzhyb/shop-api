package com.example.xb.controller;

import com.example.xb.domain.User;
import com.example.xb.domain.page.DataDomain;
import com.example.xb.domain.result.AjaxResult;
import com.example.xb.domain.result.ResultInfo;
import com.example.xb.service.IUserService;
import com.example.xb.utils.JwtUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/api/user")
@Api(tags = "用户管理")
public class UserController extends  BaseController {
    @Autowired
    private IUserService userService;

    /**
     * 获取用户列表
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "分页获取用户信息", notes = "分页获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页数量", defaultValue = "10")
    }
    )
    public AjaxResult list(@ApiIgnore()User user, String current, String size)
    {
        DataDomain dd= new DataDomain();
        dd.setCurrent(current);
        dd.setSize(size);
        ResultInfo resultInfo=startPage(dd);
        List<User> list = userService.selectUserList(user);
        dd.setRecords(list);
        PageInfo pageInfo= new PageInfo(list);
        dd.setTotal(pageInfo.getTotal());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", 1002);
        map.put("userName", "张晓明");
        map.put("age", 12);
        map.put("address", "山东省青岛市李沧区");
        String token = JwtUtil.createToken(map);
        System.out.println(token);
        Map<String, Object> map2 = JwtUtil.parseToken(token);
        System.out.println(map2);
        return new AjaxResult(resultInfo,resultInfo.getCode().equals("1")?null:dd);
    }
}

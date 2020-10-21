package com.example.xb.controller;


import com.example.xb.domain.LoginBody;
import com.example.xb.domain.LoginUser;
import com.example.xb.domain.User;
import com.example.xb.domain.page.DataDomain;
import com.example.xb.domain.result.AjaxResult;
import com.example.xb.domain.result.ResultInfo;
import com.example.xb.utils.JwtUtil;
import com.example.xb.utils.RedisCache;
import com.example.xb.utils.TokenUtil;
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
import java.util.Map;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/v1/login")
@Api(tags = "登录")
public class LoginController {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "登录")
    public AjaxResult list(@RequestBody LoginBody loginBody) {
        ResultInfo resultInfo = new ResultInfo();

        User user = new User();
//        user2.setUserId("f9149b3a247b4106af112227e1ade9fb");
//        user2.setNickName("123");
//        user2.setUserName("456");
        user.setUserName(loginBody.getUsername());
        user.setPassword(loginBody.getPassword());
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(user);
        String token2 = tokenUtil.createToken(loginUser);
        System.out.println(token2);
        Map<String, Object> map3 = jwtUtil.parseToken(token2);
        System.out.println(map3);


        Object obj = redisCache.getCacheObject("f9149b3a247b4106af112227e1ade9fb");
        System.out.println(obj);

        return new AjaxResult(resultInfo, null);
    }

}

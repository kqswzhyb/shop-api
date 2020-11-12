package com.example.xb.controller;


import com.example.xb.domain.account.LoginBody;
import com.example.xb.domain.account.LoginUser;
import com.example.xb.domain.user.User;
import com.example.xb.domain.result.AjaxResult;
import com.example.xb.domain.result.ResultInfo;
import com.example.xb.service.UserService;
import com.example.xb.utils.AESUtil;
import com.example.xb.utils.JwtUtil;
import com.example.xb.utils.RedisCache;
import com.example.xb.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/v1")
@Api(tags = "授权认证")
public class AuthController {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Value("${token.password.secret}")
    private String SECRET_KEY;

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "登录")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        ResultInfo resultInfo = new ResultInfo();

        loginBody.setPassword(AESUtil.encryptIntoHexString(loginBody.getPassword(), SECRET_KEY));

        User userDB =userService.userLogin(loginBody);

        if(StringUtils.isEmptyOrWhitespace(userDB.getUserId())) {
            resultInfo.error("用户名或密码错误");
            return new AjaxResult(resultInfo, null);
        }else {
            User user = new User();
            user.setUserId(userDB.getUserId());
            user.setUserName(loginBody.getUserName());
            user.setPassword(loginBody.getPassword());
            user.setRoleId(userDB.getRoleId());
            user.setNickName(userDB.getNickName());
            user.setPhone(userDB.getPhone());
            LoginUser loginUser = new LoginUser();
            loginUser.setUser(user);
            String token = tokenUtil.createToken(loginUser);
//            System.out.println(token);
            Map<String, Object> map = jwtUtil.parseToken(token);
            System.out.println(map);

            resultInfo.success("登录成功");

            return new AjaxResult(resultInfo, token);
        }
    }

    /**
     * 退出登录
     *
     * @return
     */
    @GetMapping("/logout")
    @ApiOperation(value = "退出登录", notes = "退出登录")
    public AjaxResult logout(HttpServletRequest req) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            redisCache.deleteObject(jwtUtil.getUserId(req));
            resultInfo.success("退出成功");
        }catch (Exception e) {
            resultInfo.error("失败");
        }
        return new AjaxResult(resultInfo, null);
    }
}

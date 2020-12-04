package com.example.xb.controller;


import com.example.xb.domain.account.LoginBody;
import com.example.xb.domain.account.LoginUser;
import com.example.xb.domain.user.User;
import com.example.xb.domain.result.AjaxResult;
import com.example.xb.domain.result.ResultInfo;
import com.example.xb.service.UserService;
import com.example.xb.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
    private ImgUtil imgUtil;

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
        if(StringUtils.isEmptyOrWhitespace(loginBody.getUid())) {
            resultInfo.error("uid为空");
            return new AjaxResult(resultInfo, null);
        }
        if(StringUtils.isEmptyOrWhitespace(loginBody.getX())) {
            resultInfo.error("x为空");
            return new AjaxResult(resultInfo, null);
        }
        if(redisCache.getCacheObject(loginBody.getUid()) == null) {
            resultInfo.error("验证码已过期");
            return new AjaxResult(resultInfo, null);
        }
        if(Integer.parseInt(loginBody.getX()) > (int) redisCache.getCacheObject(loginBody.getUid())+10 || Integer.parseInt(loginBody.getX()) < (int) redisCache.getCacheObject(loginBody.getUid())-10) {
            resultInfo.error("验证失败");
            return new AjaxResult(resultInfo, 1);
        }
        redisCache.deleteObject(loginBody.getUid());

        loginBody.setPassword(AESUtil.encryptIntoHexString(loginBody.getPassword(), SECRET_KEY));

        User userDB =userService.userLogin(loginBody);
        if(userDB==null){
            resultInfo.error("用户名不存在");
            return new AjaxResult(resultInfo, null);
        }

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
//            System.out.println(map);

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

    /**
     * 获取验证码
     *
     * @return
     */
    @GetMapping("/captcha/new")
    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    public AjaxResult captcha() throws IOException {
        int x = imgUtil.getPoint("x");
        int y = imgUtil.getPoint("y");
        String uid = "captcha-img"+UUIDUtil.NewLowUUID().substring(0,9)+System.currentTimeMillis();
        String num = new Random().nextInt(10) + 1 + "";
        BufferedImage bg = Thumbnails
                .of(new File("src/main/resources/img/pic" + num + ".jpg"))
                .scale(0.5)
                .watermark((int enclosingWidth, int enclosingHeight, int width, int height, int insetLeft, int insetRight, int insetTop, int insetBottom) -> {
                    return new Point(x, y);
                }, ImageIO.read(new File("src/main/resources/img/wh.png")), 0.5f)
                .asBufferedImage();
        BufferedImage puzzle = Thumbnails
                .of(new File("src/main/resources/img/pic" + num + ".jpg"))
                .scale(0.5)
                .sourceRegion(x * 2, y * 2, 80, 80)
                .asBufferedImage();
//        System.out.println(x);
//        System.out.println(y);
        redisCache.setCacheObject(uid, x, 60000, TimeUnit.MILLISECONDS);
        Map<String,String> map = new HashMap<>();
        map.put("puzzle",imgUtil.getBase64(puzzle));
        map.put("bg",imgUtil.getBase64(bg));
        map.put("y",y+"");
        map.put("uid",uid);
        return new AjaxResult(new ResultInfo(), map);
    }
}

package com.example.xb.utils;


import com.example.xb.domain.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class TokenUtil {

    @Autowired
    private RedisCache redisCache;


    public  String createToken(LoginUser loginUser)
    {
        String token = UUID.fromString(loginUser.getUser().getUserId()).toString();
        Map<String, Object> claims = new HashMap<>();
        claims.put("login_tokens:", token);
        claims.put("user_name", loginUser.getUser().getUserName());
        claims.put("nick_name", loginUser.getUser().getNickName());
        String jwt =JwtUtil.createToken(claims);
        redisCache.setCacheObject(loginUser.getUser().getUserId(), jwt, 30, TimeUnit.MINUTES);

        return jwt;
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public String refreshToken(LoginUser loginUser)
    {
        String token = UUID.fromString(loginUser.getUser().getUserId()).toString();
        String userId = loginUser.getUser().getUserId();
        Map<String, Object> claims = new HashMap<>();
        claims.put("login_tokens:", token);
        claims.put("user_name", loginUser.getUser().getUserName());
        claims.put("nick_name", loginUser.getUser().getNickName());
        String jwt =JwtUtil.createToken(claims);
        redisCache.setCacheObject(userId, loginUser, 30, TimeUnit.MINUTES);

        return jwt;
    }

}

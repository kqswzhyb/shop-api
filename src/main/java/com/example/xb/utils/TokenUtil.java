package com.example.xb.utils;


import com.example.xb.domain.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class TokenUtil {

    @Autowired
    private RedisCache redisCache;


    public String createToken(LoginUser loginUser) {
        String jwt = loginToken(loginUser);
        redisCache.setCacheObject(loginUser.getUser().getUserId(), jwt, 30, TimeUnit.MINUTES);

        return jwt;
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public String refreshToken(LoginUser loginUser) {
        String userId = loginUser.getUser().getUserId();
        String jwt = loginToken(loginUser);
        redisCache.setCacheObject(userId, loginUser, 30, TimeUnit.MINUTES);

        return jwt;
    }

    public String loginToken(LoginUser loginUser) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_name", loginUser.getUser().getUserName());
        claims.put("nick_name", loginUser.getUser().getNickName());
        claims.put("user_id", loginUser.getUser().getUserId());
        claims.put("role_id", loginUser.getUser().getRoleId());
        claims.put("phone", loginUser.getUser().getPhone());

        return JwtUtil.createToken(claims);
    }

}

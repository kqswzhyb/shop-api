package com.example.xb.utils;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtUtil {

    /**
     * key（按照签名算法的字节长度设置key）
     */
    @Value("${token.secret}")
    private String SECRET_KEY;
    /**
     * 过期时间（毫秒单位）
     */
    @Value("${token.expireTime}")
    private long TOKEN_EXPIRE_MILLIS;

    /**
     * 创建token
     *
     * @param claimMap
     * @return
     */
    public String createToken(Map<String, Object> claimMap) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(currentTimeMillis))    // 设置签发时间
                .setExpiration(new Date(currentTimeMillis + TOKEN_EXPIRE_MILLIS))   // 设置过期时间
                .addClaims(claimMap)
                .signWith(SignatureAlgorithm.HS256, generateKey())
                .compact();
    }

    /**
     * 验证token
     *
     * @param token
     * @return 0 验证成功，1、2、3、4、5 验证失败
     */
    public int verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(generateKey()).parseClaimsJws(token);
            return 0;
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            return 1;
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
            return 2;
        } catch (MalformedJwtException e) {
            e.printStackTrace();
            return 3;
        } catch (SignatureException e) {
            e.printStackTrace();
            return 4;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return 5;
        }
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public Map<String, Object> parseToken(String token) {
        return Jwts.parser()  // 得到DefaultJwtParser
                .setSigningKey(generateKey()) // 设置签名密钥
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 生成安全密钥
     *
     * @return
     */
    public Key generateKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    /**
     * 获取请求中的token
     *
     * @param req
     * @return
     */
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}

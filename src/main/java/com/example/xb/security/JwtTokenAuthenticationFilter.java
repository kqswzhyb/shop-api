package com.example.xb.security;

import com.example.xb.utils.JwtUtil;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenAuthenticationFilter extends GenericFilterBean {
    @Autowired
    private final JwtUtil jwtUtil;

    public JwtTokenAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) res;
        try {
            String token = jwtUtil.resolveToken((HttpServletRequest) req);
            if (token != null) {
                jwtUtil.verifyToken(token);
                Authentication auth = jwtUtil.getAuthentication(token);

                if (auth != null) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            //若有异常统一抛出401，token认证失败
            httpServletResponse.sendError(401,"认证失败");
        }
        filterChain.doFilter(req, res);
    }

}

package com.example.xb.config;

import com.example.xb.domain.result.AjaxResult;
import com.example.xb.domain.result.ResultInfo;
import com.example.xb.security.JwtTokenAuthenticationFilter;
import com.example.xb.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Administrator
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 设置请求的统一前缀
     */
    @Value("${swagger.pathMapping}")
    private String pathMapping;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/csrf").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/swagger**").permitAll()
//        .antMatchers(pathMapping+"/user/**").permitAll()
                .antMatchers(pathMapping + "/login").permitAll()
                .antMatchers(HttpMethod.OPTIONS, pathMapping + "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler((req, rsp, e) ->  {
                    ResultInfo resultInfo =new ResultInfo();
                    rsp.setContentType(" application / json; charset = UTF-8");
                    rsp.setStatus(HttpStatus.FORBIDDEN.value());
                    resultInfo.error("拒绝访问");
                    rsp.getWriter().write(new ObjectMapper().writeValueAsString(new AjaxResult(resultInfo, null)));
                })
                .authenticationEntryPoint((req, rsp, e) ->{
                    ResultInfo resultInfo =new ResultInfo();
                    rsp.setContentType(" application / json; charset = UTF-8");
                    rsp.setStatus(HttpStatus.UNAUTHORIZED.value());
                    resultInfo.error("未认证");
                    rsp.getWriter().write(new ObjectMapper().writeValueAsString(new AjaxResult(resultInfo, null)));
                })
                .and()
                .addFilterBefore(new JwtTokenAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
    }
}
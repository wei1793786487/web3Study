package com.example.web3study.security.handler;

import com.alibaba.fastjson.JSON;
import com.example.web3study.security.JwtUtils;
import com.example.web3study.security.Payload;
import com.example.web3study.utils.JsonWriteUtlis;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Devil
 * @date 2020/4/15 12:39
 * 登录认证的过滤器
 */
@SuppressWarnings("all")
@Slf4j
public class CustomizeVerifyFilter extends BasicAuthenticationFilter {




    public CustomizeVerifyFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);


    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (!StringUtils.hasText(header)){
               chain.doFilter(request,response);
        }
        //systeminfo 可以放在缓存里面 然后从缓存里面拿
//        JwtUtils.getInfoFromToken()

    }
}
package com.example.web3study.security.handler;

import com.example.web3study.exception.XxException;
import com.example.web3study.pojo.SystemInfo;
import com.example.web3study.pojo.Users;
import com.example.web3study.pojo.common.ResultData;
import com.example.web3study.pojo.common.ReturnCode;
import com.example.web3study.security.JwtUtils;
import com.example.web3study.security.Payload;
import com.example.web3study.security.jwtUser;
import com.example.web3study.security.provider.PhoneLoginFilterAuthenticationToken;
import com.example.web3study.security.provider.PrivateKeyLoginFilterAuthenticationToken;
import com.example.web3study.service.SystemInfoService;
import com.example.web3study.utils.JsonWriteUtlis;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Devil
 * @date 2020/4/15 12:39
 * 登录认证的过滤器
 */
@SuppressWarnings("all")
@Slf4j
public class CustomizeJwtVerifyFilter extends BasicAuthenticationFilter {

    SystemInfoService systemInfoService;


    public CustomizeJwtVerifyFilter(AuthenticationManager authenticationManager, SystemInfoService systemInfoService) {
        super(authenticationManager);
        this.systemInfoService=systemInfoService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String token = request.getHeader("Authorization");
            if (!StringUtils.hasText(token)){
                   chain.doFilter(request,response);
            }
            SystemInfo systemInfo = systemInfoService.getSystemInfo();
            Payload<jwtUser> infoFromToken = JwtUtils.getInfoFromToken(token, systemInfo, jwtUser.class);
            Date date = new Date();
            Date expiration = infoFromToken.getExpiration();
            if (date.after(expiration)){
                  throw new XxException(ReturnCode.TOKEN_EXP);
            }
            jwtUser userInfo = infoFromToken.getUserInfo();
            Integer type = userInfo.getType();
            if (type==0){
                //好不优雅啊草
                List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + "ADMIN"));
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userInfo.getName(), "", grantedAuthorities);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }else if (type==1){
                List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + "USERS"));
                //第一次获取的时候呀判断登录平台 现在不需要了
                PhoneLoginFilterAuthenticationToken phoneLoginFilterAuthenticationToken =
                        new PhoneLoginFilterAuthenticationToken(userInfo.getPhone(),"","reflash", grantedAuthorities);
                SecurityContextHolder.getContext().setAuthentication(phoneLoginFilterAuthenticationToken);

            }else if (type==2){
                //就算是秘钥登录也算作手机登录了
                List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + "USERS"));
                //第一次获取的时候呀判断登录平台 现在不需要了
                PhoneLoginFilterAuthenticationToken phoneLoginFilterAuthenticationToken =
                        new PhoneLoginFilterAuthenticationToken(userInfo.getPhone(),"","reflash", grantedAuthorities);
                SecurityContextHolder.getContext().setAuthentication(phoneLoginFilterAuthenticationToken);

            }else {
                JsonWriteUtlis.sendJson(response, ResultData.fail(ReturnCode.TOKEN_EXP));
            }

            //没有问题之后再放行过滤器
            chain.doFilter(request, response);
        }catch (Exception e) {
            // tdod 去除
            throw e;
            //如果是时间过期的异常的话
//            if (e instanceof ExpiredJwtException) {
//                Claims claims = ((ExpiredJwtException) e).getClaims();
//                JsonWriteUtlis.sendJson(response, ResultData.fail(ReturnCode.TOKEN_EXP));
//                  //过期
////                String userString = claims.get("user", String.class);
////                SysUser user = JSON.parseObject(userString, SysUser.class);
////
////                //如果过期了 那么看看redis里面有没有缓存
////                String refresh = redisTemplate.opsForValue().get(redisProperties.getTokenPre() + user.getId());
////                Boolean isoverTime = TimeUtils.CalculateTime(user.getLasttime(), redisProperties.getDisparity());
////                if (refresh != null && isoverTime) {
////                    //如果redis里面有缓存的话并且没有超过一定时间没有登录，那么生成新的token.
////                    String newToken = JwtUtils.generateTokenExpireInMinutes(user, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
////                    JsonResult result = ResultTool.fail(ResultCode.TOKEN_REFRESH, newToken);
////                    JsonWriteUtlis.fail(request, response, result);
////                } else {
////                    JsonResult result = ResultTool.fail(ResultCode.USER_ACCOUNT_EXPIRED);
////                    JsonWriteUtlis.fail(request, response, result);
////                }
//            } else {
//                JsonWriteUtlis.sendJson(response, ResultData.fail(ReturnCode.TOKEN_EXP));
//            }
        }
    }

}
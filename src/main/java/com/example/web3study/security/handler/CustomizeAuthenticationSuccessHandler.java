package com.example.web3study.security.handler;

import com.example.web3study.pojo.Admin;
import com.example.web3study.security.SecurityTool;
import com.example.web3study.security.jwtUser;
import com.example.web3study.utils.JsonWriteUtlis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Devil
 * @date 2020/1/5 16:48
 * 自定义登录的处理的逻辑
 */
@Component
@Slf4j
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Autowired
    private  SecurityTool securityTool;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof Admin){
            Admin admin = (Admin) principal;
            jwtUser jwtUser = new jwtUser().setName(admin.getName()).setPhone(admin.getPhone()).setUid(admin.getId());
            String jwt = securityTool.generateJwt(jwtUser);
            JsonWriteUtlis.sendJsonSuccess(response,jwt);
        }
    }
}
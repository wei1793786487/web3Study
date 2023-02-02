package com.example.web3study.security.handler;

import com.example.web3study.pojo.Admin;
import com.example.web3study.pojo.Users;
import com.example.web3study.security.SecurityTool;
import com.example.web3study.security.jwtUser;
import com.example.web3study.security.provider.PhoneLoginFilterAuthenticationToken;
import com.example.web3study.security.provider.PrivateKeyLoginFilterAuthenticationToken;
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

        //用户登录的id存放进redis里面 以便于删除jwt用户 下面两个setdetail里面 放上id 然后这里取出
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof Admin){
            Admin admin = (Admin) principal;
            jwtUser jwtUser = new jwtUser()
                    .setName(admin.getName())
                    .setPhone(admin.getPhone())
                    .setType(0)
                    .setUid(admin.getId()).setRole("admin");
            String jwt = securityTool.generateJwt(jwtUser);
            JsonWriteUtlis.sendJsonSuccess(response,jwt);
        }else if (authentication instanceof PhoneLoginFilterAuthenticationToken){
            PhoneLoginFilterAuthenticationToken principal1 = (PhoneLoginFilterAuthenticationToken) authentication;
            writeUserToken(principal1,response,1);
        }else if (authentication instanceof PrivateKeyLoginFilterAuthenticationToken){
            PrivateKeyLoginFilterAuthenticationToken principal1 = (PrivateKeyLoginFilterAuthenticationToken) authentication;
            writeUserToken(principal1,response,2);
        }
        else {
            //不会触发这个 万一有黑客触发了 骂他
            JsonWriteUtlis.sendJsonSuccess(response,"sb");
        }

    }
    public void  writeUserToken(Authentication authentication,HttpServletResponse response,Integer type) throws IOException {
        Users users = (Users) authentication.getDetails();
        jwtUser jwtUser = new jwtUser()
                .setPhone(users.getPhone())
                .setUid(users.getId())
                .setType(type)
                .setRole("user");
        String jwt = securityTool.generateJwt(jwtUser);
        JsonWriteUtlis.sendJsonSuccess(response,jwt);
    }
}
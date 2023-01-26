package com.example.web3study.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class PhoneLoginFilter extends AbstractAuthenticationProcessingFilter {
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/user/login",
            "POST");
    public static final String SPRING_SECURITY_FORM_PHONE_NUMBER = "phone_number";
    public static final String SPRING_SECURITY_FORM_PHONE_CODE = "phone_code";
    public static final String SPRING_SECURITY_FORM_PHONE_PASSWORD = "password";

    public PhoneLoginFilter() {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        String phoneNumber = obtainPhoneNumber(request);
        if (!StringUtils.hasText(phoneNumber)){
            throw new BadCredentialsException(SPRING_SECURITY_FORM_PHONE_NUMBER+"不存在,请输入手机号");
        }
        String phoneCode = obtainPhoneCode(request);
        String phonePassword = obtainPhonePassword(request);
        if (!StringUtils.hasText(phonePassword)&&!StringUtils.hasText(phoneCode)){
            throw new BadCredentialsException(SPRING_SECURITY_FORM_PHONE_CODE+"或"+SPRING_SECURITY_FORM_PHONE_PASSWORD+"缺少");
        }
        PhoneLoginFilterAuthenticationToken phoneLoginFilterAuthenticationToken=null;
        if (!StringUtils.hasText(phonePassword)&&StringUtils.hasText(phoneCode)){
            //有密码没有手机码 说明是密码登录
            phoneLoginFilterAuthenticationToken = new PhoneLoginFilterAuthenticationToken(phoneNumber,phoneCode,"code");
        }else if (StringUtils.hasText(phonePassword)&&!StringUtils.hasText(phoneCode)){
            //有手机 说明是手机登录
             phoneLoginFilterAuthenticationToken = new PhoneLoginFilterAuthenticationToken(phoneNumber,phonePassword,"password");
        }else {
            throw new BadCredentialsException("登录你个头");
        }
        setDetails(request,phoneLoginFilterAuthenticationToken);
        return this.getAuthenticationManager().authenticate(phoneLoginFilterAuthenticationToken);
    }
    protected String obtainPhoneNumber(HttpServletRequest request) {
        String privateKey =request.getParameter(SPRING_SECURITY_FORM_PHONE_NUMBER);
        return privateKey == null ? "" : privateKey.trim();
    }
    protected String obtainPhoneCode(HttpServletRequest request) {
        String privateKey =request.getParameter(SPRING_SECURITY_FORM_PHONE_CODE);
        return privateKey == null ? "" : privateKey.trim();
    }
    protected String obtainPhonePassword(HttpServletRequest request) {
        String privateKey =request.getParameter(SPRING_SECURITY_FORM_PHONE_PASSWORD);
        return privateKey == null ? "" : privateKey.trim();
    }
    protected void setDetails(HttpServletRequest request,
                              PhoneLoginFilterAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }


}


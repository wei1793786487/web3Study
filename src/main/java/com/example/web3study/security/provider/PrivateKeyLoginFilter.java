package com.example.web3study.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class PrivateKeyLoginFilter extends AbstractAuthenticationProcessingFilter {
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/private_key_login",
            "POST");
    public static final String SPRING_SECURITY_FORM_PRIVATE_KEY = "private_key";
    public PrivateKeyLoginFilter() {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String privateKey = obtainPrivateKey(request);
        privateKey = privateKey.trim();
        if (!StringUtils.hasText(privateKey)){
            throw new BadCredentialsException(SPRING_SECURITY_FORM_PRIVATE_KEY+"参数不存在");
        }
        PrivateKeyLoginFilterAuthenticationToken privateKeyLoginFilterAuthenticationToken = new PrivateKeyLoginFilterAuthenticationToken(privateKey);
        setDetails(request,privateKeyLoginFilterAuthenticationToken);
        return this.getAuthenticationManager().authenticate(privateKeyLoginFilterAuthenticationToken);
    }
    protected String obtainPrivateKey(HttpServletRequest request) {
        String privateKey =request.getParameter(SPRING_SECURITY_FORM_PRIVATE_KEY);
        return privateKey == null ? "" : privateKey;
    }
    protected void setDetails(HttpServletRequest request,
                              PrivateKeyLoginFilterAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }


}


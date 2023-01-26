package com.example.web3study.security.provider;


import com.example.web3study.pojo.Users;
import com.example.web3study.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sun
 */
@Slf4j
public class PhoneLoginAuthenticationProvider implements AuthenticationProvider {

    UsersService usersService;
    PasswordEncoder passwordEncoder;

    public PhoneLoginAuthenticationProvider(UsersService usersService, PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PhoneLoginFilterAuthenticationToken my_authentication = (PhoneLoginFilterAuthenticationToken) authentication;
        if ("code".equals(my_authentication.getPlatform())){
                throw new BadCredentialsException("没钱买短信服务 v我50 我去买1000条去");
        }else if ("password".equals(my_authentication.getPlatform())){
            Users users = usersService.finOneByPhone((String) my_authentication.getPrincipal());
            if (ObjectUtils.isEmpty(users)){
                throw new BadCredentialsException("该手机号没有注册");
            }
            boolean matches = passwordEncoder.matches((String) my_authentication.getCredentials(), users.getPassword());
            if (!matches){
                throw new BadCredentialsException("密码错误");
            }
            Byte status = users.getStatus();
            if (status!=1){
                throw new LockedException("账号已经冻结了");
            }
            List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + "USERS"));
            PhoneLoginFilterAuthenticationToken phoneLoginFilterAuthenticationToken =
                    new PhoneLoginFilterAuthenticationToken(my_authentication.getPrincipal(),my_authentication.getCredentials(),my_authentication.getPlatform(), grantedAuthorities);
            phoneLoginFilterAuthenticationToken.setDetails(users);
            return phoneLoginFilterAuthenticationToken;
        }else {
            throw new BadCredentialsException("哦 66666");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {

        return PhoneLoginFilterAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

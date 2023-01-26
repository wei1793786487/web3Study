package com.example.web3study.security.provider;


import com.example.web3study.pojo.Admin;
import com.example.web3study.pojo.Users;
import com.example.web3study.service.AdminService;
import com.example.web3study.service.BlockchainUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PrivateKeyLoginAuthenticationProvider implements AuthenticationProvider {
    BlockchainUserService blockchainUserService;

    public  PrivateKeyLoginAuthenticationProvider(BlockchainUserService blockchainUserService) {
        this.blockchainUserService = blockchainUserService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String privateKey = (String) authentication.getPrincipal();
            // 管理员不再使用秘钥登录 仅密码登录
            Users usersByPrivateKey = blockchainUserService.findUsersByPrivateKey(privateKey);
            if(ObjectUtils.isEmpty(usersByPrivateKey)){
                throw new BadCredentialsException("未发现对应私钥");
            }
            List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + "USERS"));
            PrivateKeyLoginFilterAuthenticationToken users = new PrivateKeyLoginFilterAuthenticationToken(usersByPrivateKey, grantedAuthorities);
            users.setDetails(usersByPrivateKey);
            return users;
        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {

        return PrivateKeyLoginFilterAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

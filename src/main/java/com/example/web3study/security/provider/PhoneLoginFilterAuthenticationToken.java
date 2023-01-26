package com.example.web3study.security.provider;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class PhoneLoginFilterAuthenticationToken extends AbstractAuthenticationToken {


    private final Object principal;

    private final Object credential;


    private final String platform;

    public PhoneLoginFilterAuthenticationToken(Object principal, Object credential, String platform) {
        super( null);
        this.principal = principal;
        this.credential = credential;
        this.platform = platform;
        setDetails(platform);
        setAuthenticated(false);
    }
    public PhoneLoginFilterAuthenticationToken(Object principal,  Object credential, String platform,Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credential = credential;
        this.platform = platform;
        setDetails(platform);
        super.setAuthenticated(true);
    }


    @Override
    public Object getCredentials() {
        return credential;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }


    public String getPlatform() {
        return platform;
    }
}

package com.example.web3study.security.provider;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class PrivateKeyLoginFilterAuthenticationToken extends AbstractAuthenticationToken {


    private final Object principal;

    public PrivateKeyLoginFilterAuthenticationToken(Object principal) {
        super( null);
        this.principal = principal;
        setAuthenticated(false);
    }
    public PrivateKeyLoginFilterAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}

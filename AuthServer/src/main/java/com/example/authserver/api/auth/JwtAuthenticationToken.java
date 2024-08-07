package com.example.authserver.api.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private String jwtToken;
    private Object principal;
    private Object credentials;

    public JwtAuthenticationToken(String jwtToken) {
        super(null);
        this.jwtToken = jwtToken;
        super.setAuthenticated(false);
    }

    public JwtAuthenticationToken(
            Object principal,
            Object credentials,
            Collection<? extends GrantedAuthority> authorities
    ) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public String getJwtToken() {
        return this.jwtToken;
    }
}

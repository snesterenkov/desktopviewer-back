package com.eklib.desktopviewer.security.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by vadim on 23.10.2014.
 */
public class APIKeyAuthenticationToken extends AbstractAuthenticationToken {

    private final APIKeyAuthenticationCredentials credentials;
    private final APIKeyAuthenticationPrincipal principal;

    public APIKeyAuthenticationToken(APIKeyAuthenticationPrincipal principal, APIKeyAuthenticationCredentials credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    public APIKeyAuthenticationToken(APIKeyAuthenticationPrincipal principal, APIKeyAuthenticationCredentials credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        // must use super, as we override
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

    public void setAuthenticated(boolean isAuthenticated) {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }
}

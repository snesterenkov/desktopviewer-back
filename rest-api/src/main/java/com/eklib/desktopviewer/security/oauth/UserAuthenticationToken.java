package com.eklib.desktopviewer.security.oauth;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * Created by alex on 09.12.2014.
 */
public class UserAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = -1092219614309982278L;
    private final Object principal;
    private Object credentials;

    public UserAuthenticationToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    public Object getCredentials() {
        return this.credentials;
    }

    public Object getPrincipal() {
        return this.principal;
    }
}

package com.eklib.desktopviewer.security.oauth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by alex on 09.12.2014.
 */
public class UserAuthenticationProvider implements AuthenticationProvider {


    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        UserAuthenticationToken auth = new UserAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials());
        return auth;
    }

    public boolean supports(Class<?> arg0) {
        return true;
    }
}

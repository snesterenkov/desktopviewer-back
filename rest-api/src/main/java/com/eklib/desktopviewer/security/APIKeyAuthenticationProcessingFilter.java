package com.eklib.desktopviewer.security;

import com.eklib.desktopviewer.security.maodel.APIKeyAuthenticationCredentials;
import com.eklib.desktopviewer.security.maodel.APIKeyAuthenticationPrincipal;
import com.eklib.desktopviewer.security.maodel.APIKeyAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by vadim on 23.10.2014.
 */
public class APIKeyAuthenticationProcessingFilter extends GenericFilterBean {

    protected static final String PARAM_CLIENT = "client";
    protected static final String PARAM_SIGNATURE = "signature";

    private AuthenticationManager authenticationManager;

    //Uri from version to resource
    private static final String DESKTOPVIEWER_REGEX= ".*(/v[0-9]+)";

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(this.authenticationManager, "An AuthenticationManager is required");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Assert.isNull(filterChain, "Because filterchain is performed by PopProductionSecurityFilterWrapper, it must be null");

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String apiClientKey = request.getParameter(PARAM_CLIENT);
        String signature = request.getParameter(PARAM_SIGNATURE);

        if (apiClientKey != null) {
            APIKeyAuthenticationPrincipal principal = new APIKeyAuthenticationPrincipal(apiClientKey);

            String regex = DESKTOPVIEWER_REGEX;

            APIKeyAuthenticationCredentials credentials = new APIKeyAuthenticationCredentials(signature, httpRequest.getRequestURI().replaceFirst(regex, "$1"), httpRequest.getParameterMap());

            APIKeyAuthenticationToken authRequest = new APIKeyAuthenticationToken(principal, credentials);

            try {
                Authentication authResult = this.authenticationManager.authenticate(authRequest);
                SecurityContextHolder.getContext().setAuthentication(authResult);
            } catch (AuthenticationException exception) {
                logger.error("Authentication failed for " + httpRequest.getRemoteAddr() + " on " + httpRequest.getRequestURI(), exception);
                SecurityContextHolder.getContext().setAuthentication(null);
            }
        }
    }

    protected AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

}
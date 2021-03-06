package com.eklib.desktopviewer.security;

import com.eklib.desktopviewer.security.model.APIKeyAuthenticationCredentials;
import com.eklib.desktopviewer.security.model.APIKeyAuthenticationPrincipal;
import com.eklib.desktopviewer.security.model.APIKeyAuthenticationToken;
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

    //Uri from version to resource
    private static final String DESKTOPVIEWER_REGEX= ".*(/v[0-9]+)";

    private AuthenticationManager authenticationManager;


    @Override
    public void afterPropertiesSet() {
        Assert.notNull(this.authenticationManager, "An AuthenticationManager is required");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Assert.isNull(filterChain, "Because filterchain is performed by PopProductionSecurityFilterWrapper, it must be null");
        Authentication authRequest = null;
        APIKeyAuthenticationCredentials credentials = null;
        APIKeyAuthenticationPrincipal principal = null;
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String apiClientKey = request.getParameter(PARAM_CLIENT);
        String signature = request.getParameter(PARAM_SIGNATURE);

        String regex = DESKTOPVIEWER_REGEX;


        if (apiClientKey != null) {
            principal = new APIKeyAuthenticationPrincipal(apiClientKey);



            if (signature != null) {
                credentials = new APIKeyAuthenticationCredentials(signature, httpRequest.getRequestURI().replaceFirst(regex, "$1"), httpRequest.getParameterMap());

                authRequest = new APIKeyAuthenticationToken(principal, credentials);
            }

            try {
                Authentication authResult = this.authenticationManager.authenticate(authRequest);
                SecurityContextHolder.getContext().setAuthentication(authResult);
            } catch (AuthenticationException exception) {
                logger.info(exception);
                logger.warn("Authentication failed for " + httpRequest.getRemoteAddr() + " on " + httpRequest.getRequestURI());
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

package com.eklib.desktopviewer.security;

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
public class DesktopviewerSecurityFilterWrapper extends GenericFilterBean {

    private GenericFilterBean apiKeyAuthenticationFilter;

    @Override
    public void afterPropertiesSet() throws ServletException {
        Assert.notNull(this.apiKeyAuthenticationFilter, "The apiKeyAuthenticationFilter is required");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        this.apiKeyAuthenticationFilter.doFilter(request, response, null);
        filterChain.doFilter(request, response);
        //As API is stateless, removes authentication information
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    /**
     * @param apiKeyAuthenticationFilter the apiKeyAuthenticationFilter to set
     */
    public void setApiKeyAuthenticationFilter(GenericFilterBean apiKeyAuthenticationFilter) {
        this.apiKeyAuthenticationFilter = apiKeyAuthenticationFilter;
    }
}

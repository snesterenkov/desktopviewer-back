package com.eklib.desktopviewer.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vadim on 23.10.2014.
 */
public class APIAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Integer ERROR_PAGE = 401;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //Always failed here because request analysis is done in APIAuthenticationProcessingFilter
        response.sendError(ERROR_PAGE);
    }
}

package com.eklib.desktopviewer.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vadim on 23.10.2014.
 */
public class DesktopviewerAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private APIAuthenticationEntryPoint apiAuthenticationEntryPoint;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Assert.notNull(this.apiAuthenticationEntryPoint, "The apiAuthenticationEntryPoint is required");
        this.apiAuthenticationEntryPoint.commence(request, response, exception);
    }

    /**
     * @param apiAuthenticationEntryPoint the apiAuthenticationEntryPoint to set
     */
    public void setApiAuthenticationEntryPoint(APIAuthenticationEntryPoint apiAuthenticationEntryPoint) {
        this.apiAuthenticationEntryPoint = apiAuthenticationEntryPoint;
    }
}

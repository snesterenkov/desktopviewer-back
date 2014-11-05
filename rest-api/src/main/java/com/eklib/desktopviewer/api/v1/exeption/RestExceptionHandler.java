package com.eklib.desktopviewer.api.v1.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by vadim on 03.11.2014.
 */
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value=HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorFormInfo handleUnauthorizes(AccessDeniedException ex) {

        String errorMessage = ex.getMessage();
        String code = String.valueOf(HttpStatus.UNAUTHORIZED.value());

        ErrorFormInfo errorInfo = new ErrorFormInfo(code, errorMessage);

        return errorInfo;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorFormInfo handleExeption(Exception ex) {

        String errorMessage = ex.getMessage();
        String code = String.valueOf(HttpStatus.BAD_REQUEST.value());
        ErrorFormInfo errorInfo = new ErrorFormInfo(code, errorMessage);

        return errorInfo;
    }

}

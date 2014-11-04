package com.eklib.desktopviewer.api.v1.exeption;

/**
 * Created by vadim on 03.11.2014.
 */
public class ErrorFormInfo {
    private String code;
    private String message;

    public ErrorFormInfo() {
    }

    public ErrorFormInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.eklib.desktopviewer.security.model;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by vadim on 23.10.2014.
 */
public class APIKeyAuthenticationCredentials implements Serializable {

    private String signature, uri;
    private Map parameterMap;

    public APIKeyAuthenticationCredentials(String signature, String uri, Map parameterMap) {
        this.signature = signature;
        this.uri = uri;
        this.parameterMap = parameterMap;
    }

    /**
     * @return the signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @return the parameterMap
     */
    public Map getParameterMap() {
        return parameterMap;
    }
}

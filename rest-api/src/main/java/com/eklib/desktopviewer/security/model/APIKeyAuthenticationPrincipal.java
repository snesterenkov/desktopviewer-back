package com.eklib.desktopviewer.security.model;

import java.io.Serializable;
import java.security.Principal;

/**
 * Created by vadim on 23.10.2014.
 */
public class APIKeyAuthenticationPrincipal implements Principal, Serializable {

    private String clientAPIKey;

    public APIKeyAuthenticationPrincipal(String clientAPIKey) {
        this.clientAPIKey = clientAPIKey;
    }

    @Override
    public String getName() {
        return this.clientAPIKey;
    }
}

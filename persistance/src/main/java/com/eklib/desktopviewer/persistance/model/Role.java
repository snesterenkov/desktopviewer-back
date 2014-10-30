package com.eklib.desktopviewer.persistance.model;

/**
 * Created by vadim on 23.10.2014.
 */
public enum  Role {

    DESK_USER("ROLE_DESK_USER"),
    DESK_USER_COMPANY("ROLE_DESK_USER_COMPANY"),
    DESK_ADMIN("ROLE_DESK_ADMIN");

    private final String roleName;

    private Role(String roleName){
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}

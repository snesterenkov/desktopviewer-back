package com.eklib.desktopviewer.persistance.model.security;

/**
 * Created by vadim on 23.10.2014.
 */
public enum RoleEntity {

    DESK_USER("ROLE_DESK_USER"),
    DESK_USER_COMPANY("ROLE_DESK_USER_COMPANY"),
    DESK_USER_DEPARTMENT("ROLE_DESK_USER_DEPARTMENT"),
    DESK_ADMIN("ROLE_DESK_ADMIN");

    private final String roleName;

    private RoleEntity(String roleName){
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}

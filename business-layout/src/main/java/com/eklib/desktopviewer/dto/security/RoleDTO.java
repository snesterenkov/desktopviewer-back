package com.eklib.desktopviewer.dto.security;

/**
 * Created by vadim on 23.10.2014.
 */
public enum  RoleDTO {

    DESK_USER("ROLE_DESK_USER"),
    DESK_USER_COMPANY("ROLE_DESK_USER_COMPANY"),
    DESK_ADMIN("ROLE_DESK_ADMIN");

    public static final String ROLE_DESK_USER = "ROLE_DESK_USER";
    public static final String ROLE_DESK_USER_COMPANY = "ROLE_DESK_USER_COMPANY";
    public static final String ROLE_DESK_ADMIN = "ROLE_DESK_ADMIN";

    private final String roleName;

    private RoleDTO(String roleName){
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}

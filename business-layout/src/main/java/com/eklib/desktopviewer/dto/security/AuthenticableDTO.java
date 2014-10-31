package com.eklib.desktopviewer.dto.security;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by vadim on 23.10.2014.
 */
public class AuthenticableDTO {

    private String passphrase;
    private Set<RoleDTO> roles = new HashSet<>(0);

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    public String getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }
}

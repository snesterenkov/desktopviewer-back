package com.eklib.desktopviewer.dto.security;

import com.eklib.desktopviewer.dto.security.UserDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by vadim on 28.10.2014.
 */
public class UserDetailDTO extends UserDTO {

    private String password;
    private Set<RoleDTO> roleDTOs = new HashSet<RoleDTO>(0);

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleDTO> getRoleDTOs() {
        return roleDTOs;
    }

    public void setRoleDTOs(Set<RoleDTO> roleDTOs) {
        this.roleDTOs = roleDTOs;
    }
}

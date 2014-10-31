package com.eklib.desktopviewer.dto;

import com.eklib.desktopviewer.dto.security.RoleDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * @author alex
 */
public class UserDTO extends BaseDTO {

    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private Set<RoleDTO> roleDTOs = new HashSet<RoleDTO>(0);

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleDTO> getRoleDTOs() {
        return roleDTOs;
    }

    public void setRoleDTOs(Set<RoleDTO> roleDTOs) {
        this.roleDTOs = roleDTOs;
    }
}

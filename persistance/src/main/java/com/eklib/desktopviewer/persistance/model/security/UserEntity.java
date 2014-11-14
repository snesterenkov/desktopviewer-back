package com.eklib.desktopviewer.persistance.model.security;


import com.eklib.desktopviewer.persistance.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by vadim on 17.09.2014.
 */
@Entity
@Table(name = "USER")
public class UserEntity extends BaseEntity implements Serializable {

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    @Column(name = "LOGIN", unique = true, nullable = false)
    private String login;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ROLES")
    private String roles;
    @Column(name = "EMAIL")
    private String email;

    /**
     * the separator string for roles in rolStringList.
     */
    private static final String ROLE_SEPARATOR = ",";

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    /**
     * This methods computes the pack list from internal string representation.
     * A 'common' getRoles is not used since it may be tricky because there is no way to deal with the returned set.
     *
     * @return an empty set if internal pack is null or empty
     */
    public Set<RoleEntity> readRoles() {
        if (this.roles == null || this.roles.isEmpty()) {
            return new HashSet<RoleEntity>();
        } else {
            Set<RoleEntity> retVal = new HashSet<RoleEntity>();
            for (String roleName : this.roles.split(ROLE_SEPARATOR)) {
                retVal.add(RoleEntity.valueOf(roleName.trim()));
            }
            return retVal;
        }
    }

    /**
     * Used to re-write a set of roles into the internal roleStringList representation (used for persistance).
     *
     * @param roles
     */
    public void writeRoles(final Set<RoleEntity> roles) {
        if (roles == null || roles.isEmpty()) {
            this.roles = null;
        } else {
            StringBuilder sb = new StringBuilder();
            Iterator<RoleEntity> it = roles.iterator();
            while (it.hasNext()) {
                RoleEntity role = it.next();
                sb.append(role.name());
                if (it.hasNext()) {
                    sb.append(ROLE_SEPARATOR);
                }
            }
            this.roles = sb.toString();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

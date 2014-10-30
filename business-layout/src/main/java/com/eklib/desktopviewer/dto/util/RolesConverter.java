package com.eklib.desktopviewer.dto.util;

import com.eklib.desktopviewer.dto.security.RoleDTO;
import com.eklib.desktopviewer.persistance.model.Role;
import com.eklib.desktopviewer.persistance.model.User;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by vadim on 29.10.2014.
 */
public enum RolesConverter {

    INSTANCE;

    public Set<RoleDTO> toDTO(Set<Role> roles){
        return FluentIterable.from(roles).transform(new Function<Role, RoleDTO>() {
            @Override
            public RoleDTO apply(Role entity){
                return RoleDTO.valueOf(entity.name());
            }
        }).toSet();
    }

    public Set<Role> fromDTO(Set<RoleDTO> roleDTOs){
        return FluentIterable.from(roleDTOs).transform(new Function<RoleDTO, Role>() {
            @Override
            public Role apply(RoleDTO entity){
                return Role.valueOf(entity.name());
            }
        }).toSet();
    }
}

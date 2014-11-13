package com.eklib.desktopviewer.convertor.todto.security;

import com.eklib.desktopviewer.dto.security.UserDetailDTO;
import com.eklib.desktopviewer.persistance.model.security.UserEntity;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vadim on 13.11.2014.
 */
@Component
public class UserToDetailDTO implements Function<UserEntity, UserDetailDTO>{

    @Autowired
    private RoleToDTO roleToDTO;

    @Override
    public UserDetailDTO apply(UserEntity user) {
        if(user == null){
            return null;
        }
        UserDetailDTO userDTO = new UserDetailDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoleDTOs(FluentIterable.from(user.readRoles()).transform(roleToDTO).toSet());
        return userDTO;

    }
}

package com.eklib.desktopviewer.convertor.todto.security;

import com.eklib.desktopviewer.dto.security.UserDTO;
import com.eklib.desktopviewer.persistance.model.security.UserEntity;
import com.google.common.base.Function;
import org.springframework.stereotype.Component;

/**
 * Created by vadim on 12.11.2014.
 */
@Component
public class UserToDTO implements Function<UserEntity, UserDTO>{

    @Override
    public UserDTO apply(UserEntity user) {
        if(user == null){
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}

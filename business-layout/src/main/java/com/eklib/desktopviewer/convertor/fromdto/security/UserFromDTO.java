package com.eklib.desktopviewer.convertor.fromdto.security;

import com.eklib.desktopviewer.dto.security.UserDTO;
import com.eklib.desktopviewer.persistance.model.security.UserEntity;
import com.eklib.desktopviewer.persistance.repository.security.UserRepository;
import com.google.common.base.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vadim on 12.11.2014.
 */
@Component
public class UserFromDTO implements Function<UserDTO, UserEntity> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleFromDTO roleFromDTO;

    @Override
    public UserEntity apply(UserDTO userDTO) {
        UserEntity user;
        if(userDTO == null){
            return null;
        }
        if(userDTO.getId() == null || userDTO.getId() == 0l){
            user = new UserEntity();
        } else {
            user = userRepository.findById(userDTO.getId());
        }
        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        return user;
    }
}

package com.eklib.desktopviewer.convertor.fromdto.security;

import com.eklib.desktopviewer.dto.security.UserDetailDTO;
import com.eklib.desktopviewer.persistance.model.security.UserEntity;
import com.eklib.desktopviewer.persistance.repository.security.UserRepository;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vadim on 12.11.2014.
 */
@Component
public class UserFromDetailDTO implements Function<UserDetailDTO, UserEntity> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleFromDTO roleFromDTO;

    @Override
    public UserEntity apply(UserDetailDTO userDetailDTO) {
        UserEntity user;
        if(userDetailDTO == null){
            return null;
        }
        if(userDetailDTO.getId() == null || userDetailDTO.getId() == 0L){
            user = new UserEntity();
        } else {
            user = userRepository.findById(userDetailDTO.getId());
        }
        user.setId(userDetailDTO.getId());
        user.setLogin(userDetailDTO.getLogin());
        user.setFirstName(userDetailDTO.getFirstName());
        user.setLastName(userDetailDTO.getLastName());
        user.setEmail(userDetailDTO.getEmail());
        user.setPassword(userDetailDTO.getPassword());
        user.writeRoles(FluentIterable.from(userDetailDTO.getRoleDTOs()).transform(roleFromDTO).toSet());
        return user;
    }
}

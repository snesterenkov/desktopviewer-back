package com.eklib.desktopviewer.services.security;

import com.eklib.desktopviewer.dto.UserDTO;
import com.eklib.desktopviewer.persistance.model.User;
import com.eklib.desktopviewer.persistance.repository.security.UserRepository;
import com.eklib.desktopviewer.services.BasePagingAndSortingServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServicesImpl extends BasePagingAndSortingServiceImpl<UserDTO, User, Long, UserRepository> implements UserServices {

    @Override
    public Class<User> getEntityType(){
         return User.class;
    }

    @Override
    public Class<UserDTO> getDTOType(){
        return UserDTO.class;
    }
}

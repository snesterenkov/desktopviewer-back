package com.eklib.desktopviewer.services.security;

import com.eklib.desktopviewer.dto.UserDTO;
import com.eklib.desktopviewer.dto.UserDetailDTO;
import com.eklib.desktopviewer.dto.security.AuthenticableDTO;
import com.eklib.desktopviewer.dto.security.RoleDTO;
import com.eklib.desktopviewer.persistance.model.User;
import com.eklib.desktopviewer.persistance.repository.security.UserRepository;
import com.eklib.desktopviewer.services.BaseCrudService;

import java.util.Set;


/**
 * Created by vadim on 18.09.2014.
 */
public interface UserServices extends BaseCrudService<UserDTO, User, Long, UserRepository> {

    AuthenticableDTO findAuthenticable(String name);

    UserDTO createUser(UserDetailDTO userDetailDTO);

    Set<RoleDTO> getRolesById(Long id);

    Set<RoleDTO> updateRolesById(Long id, Set<RoleDTO> roleDTOs);

    UserDTO getUserByLogin(String name);

    @Override
    @Deprecated
    UserDTO insert(UserDTO dto);
}

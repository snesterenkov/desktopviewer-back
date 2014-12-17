package com.eklib.desktopviewer.services.security;

import com.eklib.desktopviewer.dto.security.UserDTO;
import com.eklib.desktopviewer.dto.security.UserDetailDTO;
import com.eklib.desktopviewer.dto.security.AuthenticableDTO;
import com.eklib.desktopviewer.dto.security.RoleDTO;

import java.util.Collection;
import java.util.Set;


/**
 * Created by vadim on 18.09.2014.
 */
public interface UserServices {

    AuthenticableDTO findAuthenticable(String name);

    UserDTO createUser(UserDetailDTO userDetailDTO);

    Set<RoleDTO> getRolesById(Long id);

    Set<RoleDTO> updateRolesById(Long id, Set<RoleDTO> roleDTOs);

    UserDetailDTO getAutorizedUser(String name);

    UserDetailDTO getUserByEmail(String email);

    UserDTO findById(Long id);

    Collection<UserDTO> findAll();

    void delete(Long id);

    UserDTO update(Long id, UserDTO dto);
}

package com.eklib.desktopviewer.services.security;

import com.eklib.desktopviewer.dto.security.UserDTO;
import com.eklib.desktopviewer.dto.security.UserDetailDTO;
import com.eklib.desktopviewer.dto.security.AuthenticableDTO;
import com.eklib.desktopviewer.dto.security.RoleDTO;
import com.eklib.desktopviewer.persistance.model.security.UserEntity;
import com.eklib.desktopviewer.services.BaseCrudService;

import java.util.Set;


/**
 * Created by vadim on 18.09.2014.
 */
public interface UserServices extends BaseCrudService<UserDTO, UserEntity, Long> {

    AuthenticableDTO findAuthenticable(String name);

    UserDTO createUser(UserDetailDTO userDetailDTO);

    Set<RoleDTO> getRolesById(Long id);

    Set<RoleDTO> updateRolesById(Long id, Set<RoleDTO> roleDTOs);

    UserDetailDTO getAutorizedUser(String name);

    /**
     * @deprecated
     */
    @Override
    @Deprecated
    UserDTO insert(UserDTO dto);
}

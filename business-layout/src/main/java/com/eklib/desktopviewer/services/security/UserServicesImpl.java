package com.eklib.desktopviewer.services.security;

import com.eklib.desktopviewer.dto.security.AuthenticableDTO;
import com.eklib.desktopviewer.dto.security.RoleDTO;
import com.eklib.desktopviewer.persistance.model.Role;
import com.eklib.desktopviewer.persistance.model.User;
import com.eklib.desktopviewer.persistance.repository.security.UserRepository;
import com.eklib.desktopviewer.services.BasePagingAndSortingServiceImpl;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserServicesImpl extends BasePagingAndSortingServiceImpl<User, Long, UserRepository> implements UserServices {

    @Override
    public AuthenticableDTO findAuthenticable(String login) {
        User user = getRepository().getUserByName(login);
        AuthenticableDTO authenticableDTO = new AuthenticableDTO();
        authenticableDTO.setPassphrase(user.getPassword());
//        Set<RoleDTO> roleDTOs = FluentIterable.from(user.readRoles()).transform(new Function<Role, RoleDTO>() {
//            @Override
//            public RoleDTO apply(Role entity){
//                return RoleDTO.valueOf(entity.name());
//            }
//        }).toSet();
        Set<RoleDTO> roleDTOs = new HashSet<>();
        roleDTOs.add(RoleDTO.DESK_USER);
        authenticableDTO.setRoles(roleDTOs);
        return authenticableDTO;
    }
}

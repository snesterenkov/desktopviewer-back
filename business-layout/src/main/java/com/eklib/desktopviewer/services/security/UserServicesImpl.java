package com.eklib.desktopviewer.services.security;

import com.eklib.desktopviewer.dto.UserDTO;
import com.eklib.desktopviewer.dto.UserDetailDTO;
import com.eklib.desktopviewer.dto.security.AuthenticableDTO;
import com.eklib.desktopviewer.dto.security.RoleDTO;
import com.eklib.desktopviewer.dto.util.RolesConverter;
import com.eklib.desktopviewer.persistance.model.Role;
import com.eklib.desktopviewer.persistance.model.User;
import com.eklib.desktopviewer.persistance.repository.security.UserRepository;
import com.eklib.desktopviewer.services.BasePagingAndSortingServiceImpl;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Set;

@Service
@Transactional
public class UserServicesImpl extends BasePagingAndSortingServiceImpl<UserDTO, User, Long, UserRepository> implements UserServices {

    public UserServicesImpl() {
        getModelMapper().addMappings(new PropertyMap<User, UserDTO>() {
            @Override
            protected void configure() {
                map().setRoleDTOs(RolesConverter.toDTO(source.readRoles()));
            }
        });
        getModelMapper().addMappings(new PropertyMap<UserDTO, User>() {
            @Override
            protected void configure() {
                map().writeRoles(RolesConverter.fromDTO(source.getRoleDTOs()));
            }
        });
    }

    @Override
    public Class<User> getEntityType(){
         return User.class;
    }

    @Override
    public Class<UserDTO> getDTOType(){
        return UserDTO.class;
    }
    @Override
    public AuthenticableDTO findAuthenticable(String login) {
        User user = getRepository().getUserByName(login);
        if(user == null){
            return new AuthenticableDTO();
        }
        AuthenticableDTO authenticableDTO = new AuthenticableDTO();
        authenticableDTO.setPassphrase(user.getPassword());
        Set<RoleDTO> roleDTOs = FluentIterable.from(user.readRoles()).transform(new Function<Role, RoleDTO>() {
            @Override
            public RoleDTO apply(Role entity){
                return RoleDTO.valueOf(entity.name());
            }
        }).toSet();
        authenticableDTO.setRoles(roleDTOs);
        return authenticableDTO;
    }

    @Override
    public UserDTO createUser(UserDetailDTO userDetailDTO) {
        Assert.hasLength(userDetailDTO.getLogin(), "Login must not be null and not the empty String.");
        Assert.hasLength(userDetailDTO.getPassword(), "Password must not be null and not the empty String.");
        Assert.hasLength(userDetailDTO.getEmail(), "Email must not be null and not the empty String.");
        Assert.hasLength(userDetailDTO.getFirstName(), "First name must not be null and not the empty String.");
        Assert.hasLength(userDetailDTO.getLastName(), "Last name must not be null and not the empty String.");
        if(getRepository().getUserByName(userDetailDTO.getLogin()) != null){
            Assert.isTrue(false, "Login has exist");
        }
        User entity = getModelMapper().map(userDetailDTO, getEntityType());
        User newUser = getRepository().insert(entity);
        return getModelMapper().map(newUser, getDTOType());
    }

    @Override
    @Deprecated
    public UserDTO insert(UserDTO dto) {
        throw new IllegalAccessError("Direct simple call are prohibited. Use createUser(UserDetailDTO userDetailDTO)");
    }

    @Override
    public Set<RoleDTO> getRolesById(Long id) {
        User user = getRepository().findById(id);
        return RolesConverter.toDTO(user.readRoles());
    }

    @Override
    public Set<RoleDTO> updateRolesById(Long id, Set<RoleDTO> roleDTOs) {
        Set<Role> roles = RolesConverter.fromDTO(roleDTOs);
        User user = getRepository().findById(id);
        user.writeRoles(roles);
        User upUser = getRepository().update(user);
        return RolesConverter.toDTO(upUser.readRoles());
    }

    @Override
    public UserDTO getUserByLogin(String login) {
        User user = getRepository().getUserByName(login);
        UserDTO userDTO = getModelMapper().map(user, getDTOType());
//        userDTO.setRoleDTOs(RolesConverter.INSTANCE.toDTO(user.readRoles()));
        return userDTO;
    }
}

package com.eklib.desktopviewer.services.security;

import com.eklib.desktopviewer.convertor.fromdto.security.RoleFromDTO;
import com.eklib.desktopviewer.convertor.fromdto.security.UserFromDetailDTO;
import com.eklib.desktopviewer.convertor.fromdto.security.UserFromDTO;
import com.eklib.desktopviewer.convertor.todto.security.RoleToDTO;
import com.eklib.desktopviewer.convertor.todto.security.UserToDTO;
import com.eklib.desktopviewer.convertor.todto.security.UserToDetailDTO;
import com.eklib.desktopviewer.dto.security.UserDTO;
import com.eklib.desktopviewer.dto.security.UserDetailDTO;
import com.eklib.desktopviewer.dto.security.AuthenticableDTO;
import com.eklib.desktopviewer.dto.security.RoleDTO;
import com.eklib.desktopviewer.persistance.model.security.RoleEntity;
import com.eklib.desktopviewer.persistance.model.security.UserEntity;
import com.eklib.desktopviewer.persistance.repository.security.UserRepository;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.Set;

@Service
@Transactional
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserToDTO userToDTO;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserFromDTO userFromDTO;
    @Autowired
    private UserFromDetailDTO userDetailFromDTO;
    @Autowired
    private UserToDetailDTO userToDetailDTO;
    @Autowired
    private RoleToDTO roleToDTO;
    @Autowired
    private RoleFromDTO roleFromDTO;

    @Override
    public UserDTO findById(Long id) {
        return userToDTO.apply(userRepository.findById(id));
    }

    @Override
    public Collection<UserDTO> findAll() {
        return FluentIterable.from(userRepository.findAll()).transform(userToDTO).toList();
    }

    @Override
    public void delete(Long id) {
        UserEntity user = userRepository.findById(id);
        userRepository.delete(user);
    }


    //todo : rewrite
    @Override
    public AuthenticableDTO findAuthenticable(String login) {
        UserEntity user = userRepository.getUserByName(login);
        if(user == null){
            return new AuthenticableDTO();
        }
        AuthenticableDTO authenticableDTO = new AuthenticableDTO();
        authenticableDTO.setPassphrase(user.getPassword());
        Set<RoleDTO> roleDTOs = FluentIterable.from(user.readRoles()).transform(new Function<RoleEntity, RoleDTO>() {
            @Override
            public RoleDTO apply(RoleEntity entity){
                return RoleDTO.valueOf(entity.name());
            }
        }).toSet();
        authenticableDTO.setRoles(roleDTOs);
        return authenticableDTO;
    }

    @Override
    public UserDTO createUser(UserDetailDTO userDetailDTO) {
        Assert.isNull(userDetailDTO.getId(), "Id is not null");
        Assert.hasLength(userDetailDTO.getLogin(), "Login must not be null and not the empty String.");
        Assert.hasLength(userDetailDTO.getPassword(), "Password must not be null and not the empty String.");
        Assert.hasLength(userDetailDTO.getEmail(), "Email must not be null and not the empty String.");
        Assert.hasLength(userDetailDTO.getFirstName(), "First name must not be null and not the empty String.");
        Assert.hasLength(userDetailDTO.getLastName(), "Last name must not be null and not the empty String.");
        if((userRepository.getUserByName(userDetailDTO.getLogin()) != null)){
            Assert.isTrue(false, "Login or Id has exist");
        }
        UserEntity entity = userDetailFromDTO.apply(userDetailDTO);
        entity.setRoles(RoleEntity.DESK_USER.name());
        UserEntity newUser = userRepository.insert(entity);
        return userToDTO.apply(newUser);
    }

    /**
     * @deprecated
     */
    @Override
    @Deprecated
    public UserDTO insert(UserDTO dto) {
        throw new IllegalAccessError("Direct simple call are prohibited. Use createUser(UserDetailDTO userDetailDTO)");
    }

    @Override
    public Set<RoleDTO> getRolesById(Long id) {
        UserEntity user = userRepository.findById(id);
        return FluentIterable.from(user.readRoles()).transform(roleToDTO).toSet();
    }

    @Override
    public Set<RoleDTO> updateRolesById(Long id, Set<RoleDTO> roleDTOs) {
        Set<RoleEntity> roles = FluentIterable.from(roleDTOs).transform(roleFromDTO).toSet();
        UserEntity user = userRepository.findById(id);
        user.writeRoles(roles);
        UserEntity upUser = userRepository.update(user);
        return FluentIterable.from(upUser.readRoles()).transform(roleToDTO).toSet();
    }

    @Override
    public UserDetailDTO getAutorizedUser(String login) {
        UserDetailDTO userDetailDTO = userToDetailDTO.apply(userRepository.getUserByName(login));
        if(userDetailDTO != null) {
            userDetailDTO.setPassword(null);
        }
        return userDetailDTO;
    }

    @Override
    public UserDTO update(Long id, UserDTO dto) {
        dto.setId(id);
        UserEntity newUser = userFromDTO.apply(dto);
        UserEntity updatedEntity = userRepository.update(newUser);
        return userToDTO.apply(updatedEntity);
    }
}

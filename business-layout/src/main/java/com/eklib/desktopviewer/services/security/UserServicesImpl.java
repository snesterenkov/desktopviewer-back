package com.eklib.desktopviewer.services.security;

import com.eklib.desktopviewer.convertor.fromdto.security.RoleFromDTO;
import com.eklib.desktopviewer.convertor.fromdto.security.UserFromDTO;
import com.eklib.desktopviewer.convertor.fromdto.security.UserFromDetailDTO;
import com.eklib.desktopviewer.convertor.todto.security.RoleToDTO;
import com.eklib.desktopviewer.convertor.todto.security.UserToDTO;
import com.eklib.desktopviewer.convertor.todto.security.UserToDetailDTO;
import com.eklib.desktopviewer.dto.security.AuthenticableDTO;
import com.eklib.desktopviewer.dto.security.RoleDTO;
import com.eklib.desktopviewer.dto.security.UserDTO;
import com.eklib.desktopviewer.dto.security.UserDetailDTO;
import com.eklib.desktopviewer.persistance.model.security.RoleEntity;
import com.eklib.desktopviewer.persistance.model.security.UserEntity;
import com.eklib.desktopviewer.persistance.repository.security.UserRepository;
import com.eklib.desktopviewer.util.ConvertBytesUtil;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Date;
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
    @Autowired
    private JavaMailSender mailSender;

    @Value("${CHANGE_PASSWORD_MESSAGE}")
    private String CHANGE_PASSWORD_MESSAGE;
    @Value("${SUBJECT}")
    private String SUBJECT;
    @Value("${SERVER_URL}")
    private String SERVER_URL;
    @Value("${EMAIL_FROM}")
    private String EMAIL_FROM;

    @Override
    public UserDTO findById(Long id) {
        return userToDTO.apply(userRepository.findById(id));
    }

    @Override
    public Collection<UserDTO> findAll() {
        return FluentIterable.from(userRepository.findAll()).transform(userToDTO).toList();
    }

    @Override
    public Collection<UserDTO> findFreeUsers(Long projectId){
        return FluentIterable.from(userRepository.findFreeUsers(projectId)).transform(userToDTO).toList();
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
    public UserDetailDTO getUserByEmail(String email) {
        UserDetailDTO userDetailDTO = userToDetailDTO.apply(userRepository.getUserByName(email));
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


    public boolean requestOnChangingPassword(String email) {
        UserEntity user = userRepository.getUserByName(email);
        if(user != null) {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("SHA-1");
            } catch (NoSuchAlgorithmException e) {
                //toDo logs
            }
            byte[] hashValue = (email + new Date()).toString().getBytes();
            String changePasswordToken = ConvertBytesUtil.byteArray2Hex(md.digest(hashValue));

            try {
                message.setFrom(EMAIL_FROM);
                message.setTo(email);
                message.setSubject(SUBJECT);
                message.setText(CHANGE_PASSWORD_MESSAGE + " " + SERVER_URL + changePasswordToken.toString());
            } catch (MessagingException e) {
               //toDo log
            }
            mailSender.send(mimeMessage);
            user.setChangePasswordToken(changePasswordToken);
            userRepository.update(user);
            return true;
        }
        return false;
    }


    public boolean changePassword(String password, String token) {
        UserEntity user = userRepository.getUserByToken(token);
        if(user != null) {
            user.setPassword(password);
            user.setChangePasswordToken(null);
            userRepository.update(user);
            return true;
        }
        return false;
    }
}

package com.eklib.desktopviewer.services.security;

import com.eklib.desktopviewer.dto.UserDTO;
import com.eklib.desktopviewer.persistance.model.User;
import com.eklib.desktopviewer.persistance.repository.security.UserRepository;
import com.eklib.desktopviewer.services.BaseCrudService;

/**
 * Created by vadim on 18.09.2014.
 */
public interface UserServices extends BaseCrudService<UserDTO, User, Long, UserRepository> {


}

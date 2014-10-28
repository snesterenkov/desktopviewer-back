package com.eklib.desktopviewer.services.security;

import com.eklib.desktopviewer.dto.security.AuthenticableDTO;
import com.eklib.desktopviewer.persistance.model.User;
import com.eklib.desktopviewer.persistance.repository.security.UserRepository;
import com.eklib.desktopviewer.services.BaseCrudService;

import java.util.Collection;
import java.util.List;

/**
 * Created by vadim on 18.09.2014.
 */
public interface UserServices extends BaseCrudService<User, Long, UserRepository> {

    AuthenticableDTO findAuthenticable(String name);
}

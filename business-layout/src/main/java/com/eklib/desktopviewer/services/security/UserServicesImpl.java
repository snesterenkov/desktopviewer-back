package com.eklib.desktopviewer.services.security;

import com.eklib.desktopviewer.persistance.model.User;
import com.eklib.desktopviewer.persistance.repository.security.UserRepository;
import com.eklib.desktopviewer.services.BasePagingAndSortingServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl extends BasePagingAndSortingServiceImpl<User, UserRepository> implements UserServices {

}

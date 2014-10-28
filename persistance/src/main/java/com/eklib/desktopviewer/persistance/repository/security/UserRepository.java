package com.eklib.desktopviewer.persistance.repository.security;

import com.eklib.desktopviewer.persistance.model.User;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepository;


public interface UserRepository extends BasePagingAndSortingRepository<User, Long> {

    public User getUserByName(String name);
}

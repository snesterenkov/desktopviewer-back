package com.eklib.desktopviewer.persistance.repository.security;

import com.eklib.desktopviewer.persistance.model.security.UserEntity;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepository;


public interface UserRepository extends BasePagingAndSortingRepository<UserEntity, Long> {

    public UserEntity getUserByName(String name);
}

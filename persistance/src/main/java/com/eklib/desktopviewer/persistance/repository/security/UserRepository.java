package com.eklib.desktopviewer.persistance.repository.security;

import com.eklib.desktopviewer.persistance.model.companystructure.ProjectEntity;
import com.eklib.desktopviewer.persistance.model.security.UserEntity;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepository;

import java.util.List;


public interface UserRepository extends BasePagingAndSortingRepository<UserEntity, Long> {

    public UserEntity getUserByName(String name);

    List<UserEntity> findFreeUsers(Long projectId);


}

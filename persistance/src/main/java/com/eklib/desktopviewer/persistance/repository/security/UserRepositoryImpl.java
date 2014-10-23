package com.eklib.desktopviewer.persistance.repository.security;

import com.eklib.desktopviewer.persistance.model.User;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vadim on 30.09.2014.
 */
@Repository
@Transactional
public class UserRepositoryImpl extends BasePagingAndSortingRepositoryImpl<User, Long> implements UserRepository {

    @Override
    public User getUserByName() {
        return null;
    }


}

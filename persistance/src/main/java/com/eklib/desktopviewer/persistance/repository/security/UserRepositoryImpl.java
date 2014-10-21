package com.eklib.desktopviewer.persistance.repository.security;

import com.eklib.desktopviewer.persistance.model.User;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepositoryImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by vadim on 30.09.2014.
 */
@Repository
public class UserRepositoryImpl extends BasePagingAndSortingRepositoryImpl<User> implements UserRepository {

    @Override
    public User getUserByName() {
        return null;
    }

    @Override
    public Class<User> getType() {
        return User.class;
    }

    @Override
    public String getCollectionName() {
        return User.COLLECTION_NAME_USER;
    }
}

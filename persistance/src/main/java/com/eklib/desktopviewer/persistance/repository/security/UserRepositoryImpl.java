package com.eklib.desktopviewer.persistance.repository.security;

import com.eklib.desktopviewer.persistance.model.security.UserEntity;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepositoryImpl;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vadim on 30.09.2014.
 */
@Repository
@Transactional
public class UserRepositoryImpl extends BasePagingAndSortingRepositoryImpl<UserEntity, Long> implements UserRepository {

    @Override
    public UserEntity getUserByName(String name) {
        Criteria criteria = getSession().createCriteria(UserEntity.class);
        criteria.add(Restrictions.or(Restrictions.eq("login", name),Restrictions.eq("email", name)));
        return (UserEntity) criteria.uniqueResult();
    }

}

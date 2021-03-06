package com.eklib.desktopviewer.persistance.repository.security;

import com.eklib.desktopviewer.persistance.model.security.UserEntity;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepositoryImpl;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public UserEntity getUserByToken(String token) {
        Criteria criteria = getSession().createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("changePasswordToken", token));
        return (UserEntity) criteria.uniqueResult();
    }

    @Override
    public List<UserEntity> findFreeUsers(Long projectId) {
        DetachedCriteria dc = DetachedCriteria.forClass(UserEntity.class)
                .createAlias("projectEntities", "projects")
                .add(Restrictions.eq("projects.id", projectId))
                .setProjection(Projections.id());
        List<UserEntity> freeUsers = getSession().createCriteria(UserEntity.class, "user")
                .add(Subqueries.propertyNotIn("id", dc))
                .list();
        return freeUsers;
    }


}

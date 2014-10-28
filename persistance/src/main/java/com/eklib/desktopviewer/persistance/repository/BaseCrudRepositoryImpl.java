package com.eklib.desktopviewer.persistance.repository;

import com.eklib.desktopviewer.persistance.model.BaseEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

@Transactional
public abstract class BaseCrudRepositoryImpl<T extends BaseEntity, ID extends Serializable> extends BaseRepositoryImpl<T, ID> implements BaseCrudRepository<T, ID> {

    @Override
    public <S extends T> S insert(S entity) {
        Assert.notNull(entity, "The given entity must not be null!");
        getSession().save(entity);
        return entity;
    }

    @Override
    public <S extends T> S update(S entity) {
        getSession().update(entity);
        return entity;
    }

    @Override
    public <S extends T> S findById(ID id) {
        return (S) getSession().get(getPersistentClass(), id);
    }

    @Override
    public List<T> findAll() {
        return findByCriteria();
    }

    @Override
    public void delete(T entity) {
        Assert.notNull(entity, "The given entity must not be null!");
        getSession().delete(entity);
    }

    protected List<T> findByCriteria(final Criterion... criterion) {
        final Criteria crit = getSession().createCriteria(getPersistentClass());
        for (final Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
    }
}

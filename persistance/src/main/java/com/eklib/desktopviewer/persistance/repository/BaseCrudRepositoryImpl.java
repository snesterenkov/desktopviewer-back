package com.eklib.desktopviewer.persistance.repository;

import com.eklib.desktopviewer.persistance.model.BaseEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

@Transactional
public abstract class BaseCrudRepositoryImpl<E extends BaseEntity, I extends Serializable> extends BaseRepositoryImpl<E, I> implements BaseCrudRepository<E, I> {

    @Override
    public <S extends E> S insert(S entity) {
        Assert.notNull(entity, "The given entity must not be null!");
        getSession().save(entity);
        return entity;
    }

    @Override
    public <S extends E> S update(S entity) {
        getSession().update(entity);
        return entity;
    }

    @Override
    public <S extends E> S merge(S entity) {
        getSession().merge(entity);
        return entity;
    }

    @Override
    public <S extends E> S findById(I id) {
        return (S) getSession().get(getPersistentClass(), id);
    }

    @Override
    public List<E> findAll() {
        return findByCriteria();
    }

    @Override
    public void delete(E entity) {
        Assert.notNull(entity, "The given entity must not be null!");
        getSession().delete(entity);
    }

    protected List<E> findByCriteria(final Criterion... criterion) {
        final Criteria crit = getSession().createCriteria(getPersistentClass());
        for (final Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
    }
}

package com.eklib.desktopviewer.services;

import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.repository.BaseCrudRepository;

import java.util.Collection;

public abstract class BaseCrudServiceImpl<E extends BaseEntity, R extends BaseCrudRepository<E>> extends BaseServiceImpl<E, R> implements BaseCrudService<E, R> {

    @Override
    public <S extends E> S insert(S entity) {
        return getRepository().insert(entity);
    }

    @Override
    public <S extends E> Collection<S> insert(Collection<S> entities) {
        return getRepository().insert(entities);
    }

    @Override
    public <S extends E> S update(S entity) {
        return getRepository().update(entity);
    }

    @Override
    public E findById(String id) {
        return getRepository().findById(id);
    }

    @Override
    public boolean exists(String id) {
        return getRepository().exists(id);
    }

    @Override
    public Collection<E> findAll() {
        return getRepository().findAll();
    }

    @Override
    public Collection<E> findAll(Collection<String> ids) {
        return getRepository().findAll(ids);
    }

    @Override
    public long count() {
        return getRepository().count();
    }

    @Override
    public void delete(String id) {
        getRepository().delete(id);
    }

    @Override
    public void delete(E entity) {
        getRepository().delete(entity);
    }

    @Override
    public void delete(Collection<? extends E> entities) {
        getRepository().delete(entities);
    }

    @Override
    public void deleteAll() {
        getRepository().deleteAll();
    }
}

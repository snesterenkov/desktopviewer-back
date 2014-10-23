package com.eklib.desktopviewer.services;

import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.repository.BaseCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

@Transactional
public abstract class BaseCrudServiceImpl<E extends BaseEntity, ID extends Serializable, R extends BaseCrudRepository<E, ID>> extends BaseServiceImpl<E, ID, R> implements BaseCrudService<E, ID, R> {

    @Override
    public <S extends E> S insert(S entity) {
        return getRepository().insert(entity);
    }

    @Override
    public <S extends E> S update(S entity) {
        return getRepository().update(entity);
    }

    @Override
    public E findById(ID id) {
        return getRepository().findById(id);
    }

    @Override
    public Collection<E> findAll() {
        return getRepository().findAll();
    }

    @Override
    public void delete(E entity) {
        getRepository().delete(entity);
    }

    @Override
    public void delete(ID id) {
        E entity = getRepository().findById(id);
        getRepository().delete(entity);
    }
}

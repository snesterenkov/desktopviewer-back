package com.eklib.desktopviewer.services;

import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.repository.BaseCrudRepository;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by vadim on 02.10.2014.
 */
public interface BaseCrudService<E extends BaseEntity, ID extends Serializable, R extends BaseCrudRepository<E,ID>> extends BaseService<E, ID, R> {

    /**
     * Insert a given entity.
     *
     * @param entity
     * @return the insert entity
     */
    <S extends E> S insert(S entity);

    /**
     * Update a given entity.
     *
     * @param entity
     * @return the Update entity
     */
    <S extends E> S update(S entity);

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal null} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    E findById(ID id);

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    Collection<E> findAll();

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    void delete(ID id);

    /**
     * Deletes a given entity.
     *
     * @param entity
     * @throws IllegalArgumentException in case the given entity is (@literal null}.
     */
    void delete(E entity);
}

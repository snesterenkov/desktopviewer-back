package com.eklib.desktopviewer.persistance.repository;

import com.eklib.desktopviewer.persistance.model.BaseEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseCrudRepository<T extends BaseEntity, ID extends Serializable> extends BaseRepository<T, ID> {

    /**
     * Insert a given entity.
     *
     * @param entity
     * @return the insert entity
     */
    <S extends T> S insert(S entity);

    /**
     * Update a given entity.
     *
     * @param entity
     * @return the Update entity
     */
    <S extends T> S update(S entity);

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal null} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    <S extends T> S findById(ID id);

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    List<T> findAll();


    /**
     * Deletes a given entity.
     *
     * @param entity
     * @throws IllegalArgumentException in case the given entity is (@literal null}.
     */
    void delete(T entity);

}

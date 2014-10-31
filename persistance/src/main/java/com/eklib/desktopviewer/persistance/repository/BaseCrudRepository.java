package com.eklib.desktopviewer.persistance.repository;

import com.eklib.desktopviewer.persistance.model.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseCrudRepository<E extends BaseEntity, I extends Serializable> extends BaseRepository<E, I> {

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
    <S extends E> S findById(I id);

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    List<E> findAll();


    /**
     * Deletes a given entity.
     *
     * @param entity
     * @throws IllegalArgumentException in case the given entity is (@literal null}.
     */
    void delete(E entity);

}

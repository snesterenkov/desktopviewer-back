package com.eklib.desktopviewer.services;

import com.eklib.desktopviewer.dto.BaseDTO;
import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.repository.BaseCrudRepository;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by vadim on 02.10.2014.
 */
public interface BaseCrudService<D extends BaseDTO, E extends BaseEntity, I extends Serializable> {

    /**
     * Insert a given entity.
     *
     * @param dto
     * @return the insert entity
     */
     D insert(D dto);

    /**
     * Update a given entity.
     *
     * @param dto
     * @return the Update entity
     */
     D update(I id, D dto);

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal null} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    D findById(I id);

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    Collection<D> findAll();

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    void delete(I id);
}

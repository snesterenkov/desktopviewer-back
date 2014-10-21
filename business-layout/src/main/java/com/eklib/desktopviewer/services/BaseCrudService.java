package com.eklib.desktopviewer.services;

import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.repository.BaseCrudRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * Created by vadim on 02.10.2014.
 */
public interface BaseCrudService<E extends BaseEntity, R extends BaseCrudRepository<E>> extends BaseService<E, R> {

    /**
     * Insert a given entity.
     *
     * @param entity
     * @return the insert entity
     */
    <S extends E> S insert(S entity);

    /**
     * Insert all given entities.
     *
     * @param entities
     * @return the insert entities
     * @throws IllegalArgumentException in case the given entity is (@literal null}.
     */
    <S extends E> Collection<S> insert(Collection<S> entities);

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
    E findById(String id);

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be {@literal null}.
     * @return true if an entity with the given id exists, {@literal false} otherwise
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    boolean exists(String id);

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    Collection<E> findAll();

    /**
     * Returns all instances of the type with the given IDs.
     *
     * @param ids
     * @return
     */
    Collection<E> findAll(Collection<String> ids);

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
    long count();

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    void delete(String id);

    /**
     * Deletes a given entity.
     *
     * @param entity
     * @throws IllegalArgumentException in case the given entity is (@literal null}.
     */
    void delete(E entity);

    /**
     * Deletes the given entities.
     *
     * @param entities
     * @throws IllegalArgumentException in case the given {@link Collection} is (@literal null}.
     */
    void delete(Collection<? extends E> entities);

    /**
     * Deletes all entities managed by the repository.
     */
    void deleteAll();
}

package com.eklib.desktopviewer.persistance.repository;

import com.eklib.desktopviewer.persistance.model.BaseEntity;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;

import java.util.*;

public abstract class BaseCrudRepositoryImpl<T extends BaseEntity> extends BaseRepositoryImpl<T> implements BaseCrudRepository<T> {

    @Override
    public <S extends T> S insert(S entity) {
        Assert.notNull(entity, "Entity must not be null!");
        getMongoTemplate().insert(entity);
        return entity;
    }

    @Override
    public <S extends T> Collection<S> insert(Collection<S> entities) {
        Assert.notNull(entities, "The given collection of entities not be null!");
        getMongoTemplate().insertAll(entities);
        return entities;
    }

    @Override
    public <S extends T> S update(S entity) {
        Assert.notNull(entity, "Entity must not be null!");
        T find = findById(entity.getId());
        Assert.notNull(find, "Entity not found!");
        getMongoTemplate().save(entity,getCollectionName());
        return entity;
    }

    @Override
    public T findById(String id) {
        Assert.notNull(id, "The given id must not be null!");
        return getMongoTemplate().findById(id, getType());
    }

    @Override
    public boolean exists(String id) {
        Assert.notNull(id, "The given id must not be null!");
        return getMongoTemplate().exists(getIdQuery(id), getType());
    }

    @Override
    public Collection<T> findAll() {
        return findAll(new Query());
    }

    @Override
    public Collection<T> findAll(Collection<String> ids) {
        Set<String> parameters = new HashSet<String>();
        for (String id : ids) {
            parameters.add(id);
        }
        return findAll(new Query(new Criteria(getIdAttribute()).in(parameters)));
    }

    @Override
    public long count() {
        return getMongoTemplate().count(new Query(), getType());
    }

    @Override
    public void delete(String id) {
        Assert.notNull(id, "The given id must not be null!");
        getMongoTemplate().remove(getIdQuery(id), getType());
    }

    @Override
    public void delete(T entity) {
        Assert.notNull(entity, "The given entity must not be null!");
        delete(entity.getId());
    }

    @Override
    public void delete(Collection<? extends T> entities) {
        Assert.notNull(entities, "The given collection of entities not be null!");
        Set<String> ids = new HashSet<String>();
        for (T entity : entities) {
            ids.add(entity.getId());
        }
        getMongoTemplate().remove(new Query(new Criteria(getIdAttribute()).in(ids)), getType());
    }

    @Override
    public void deleteAll() {
        getMongoTemplate().remove(new Query(), getType());
    }

    private Query getIdQuery(Object id) {
        return new Query(getIdCriteria(id));
    }

    private Criteria getIdCriteria(Object id) {
        return Criteria.where(getIdAttribute()).is(id);
    }

    private List<T> findAll(Query query) {
        if (query == null) {
            return Collections.emptyList();
        }
        return getMongoTemplate().find(query, getType());
    }
}

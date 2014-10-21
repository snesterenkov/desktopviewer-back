package com.eklib.desktopviewer.persistance.repository;


import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

public abstract class BaseRepositoryImpl<T extends BaseEntity> implements BaseRepository<T> {

    @Autowired
    private MongoTemplate mongoTemplate;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public abstract Class<T> getType();

    public abstract String getCollectionName();

    public String getIdAttribute(){
        return "_id";
    }
}

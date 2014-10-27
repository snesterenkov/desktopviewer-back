package com.eklib.desktopviewer.services;

import com.eklib.desktopviewer.dto.BaseDTO;
import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Transactional
public abstract class BaseServiceImpl<D extends BaseDTO, E extends BaseEntity, ID extends Serializable, R extends BaseRepository<E, ID>> implements BaseService<E,ID, R> {

    @Autowired
    private R repository;

    @Override
    public R getRepository() {
        return repository;
    }
}

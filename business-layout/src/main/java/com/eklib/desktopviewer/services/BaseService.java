package com.eklib.desktopviewer.services;

import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.repository.BaseRepository;

import java.io.Serializable;

public interface BaseService<E extends BaseEntity, ID extends Serializable, R extends BaseRepository<E,ID>> {

    public R getRepository();

}

package com.eklib.desktopviewer.services;

import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.repository.BaseRepository;

import java.io.Serializable;

public interface BaseService<E extends BaseEntity, I extends Serializable, R extends BaseRepository<E,I>> {

    public R getRepository();

}

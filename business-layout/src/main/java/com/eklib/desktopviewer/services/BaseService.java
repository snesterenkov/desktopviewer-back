package com.eklib.desktopviewer.services;

import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.repository.BaseRepository;

public interface BaseService<E extends BaseEntity, R extends BaseRepository<E>> {

    public R getRepository();

}

package com.eklib.desktopviewer.persistance.repository;

import com.eklib.desktopviewer.persistance.model.BaseEntity;
import org.hibernate.Session;

import java.io.Serializable;

public interface BaseRepository<E extends BaseEntity, I extends Serializable> {

    public Session getSession();
}

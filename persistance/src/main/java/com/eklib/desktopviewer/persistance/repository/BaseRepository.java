package com.eklib.desktopviewer.persistance.repository;

import com.eklib.desktopviewer.persistance.model.BaseEntity;

import java.io.Serializable;

public interface BaseRepository<T extends BaseEntity, ID extends Serializable> {
}

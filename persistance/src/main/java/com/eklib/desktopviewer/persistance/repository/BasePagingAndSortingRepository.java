package com.eklib.desktopviewer.persistance.repository;

import com.eklib.desktopviewer.persistance.model.BaseEntity;

import java.io.Serializable;

// todo: think about the implementation
public interface BasePagingAndSortingRepository<E extends BaseEntity, I extends Serializable> extends BaseCrudRepository<E, I> {

}

package com.eklib.desktopviewer.services;

import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepository;

import java.io.Serializable;

//todo :  think about the implementation
public interface BasePagingAndSortingService<E extends BaseEntity, ID extends Serializable, R extends BasePagingAndSortingRepository<E, ID>> extends BaseCrudService<E,ID,R> {
}

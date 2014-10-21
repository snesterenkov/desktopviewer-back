package com.eklib.desktopviewer.services;

import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepository;

//todo :  think about the implementation
public interface BasePagingAndSortingService<E extends BaseEntity, R extends BasePagingAndSortingRepository<E>> extends BaseCrudService<E,R> {
}

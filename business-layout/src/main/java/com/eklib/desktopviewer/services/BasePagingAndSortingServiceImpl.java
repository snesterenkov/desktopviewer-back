package com.eklib.desktopviewer.services;

import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepository;

//todo :  think about the implementation
public abstract class BasePagingAndSortingServiceImpl<E extends BaseEntity, R extends BasePagingAndSortingRepository<E>>
        extends BaseCrudServiceImpl<E,R>
        implements BasePagingAndSortingService<E,R>  {
}

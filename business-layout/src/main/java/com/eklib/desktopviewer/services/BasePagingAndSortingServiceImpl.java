package com.eklib.desktopviewer.services;

import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

//todo :  think about the implementation
@Transactional
public abstract class BasePagingAndSortingServiceImpl<E extends BaseEntity, ID extends Serializable, R extends BasePagingAndSortingRepository<E, ID>>
        extends BaseCrudServiceImpl<E,ID, R>
        implements BasePagingAndSortingService<E,ID,R>  {
}

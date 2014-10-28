package com.eklib.desktopviewer.services;

import com.eklib.desktopviewer.dto.BaseDTO;
import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

//todo :  think about the implementation
@Transactional
public abstract class BasePagingAndSortingServiceImpl<D extends BaseDTO, E extends BaseEntity, ID extends Serializable, R extends BasePagingAndSortingRepository<E, ID>>
        extends BaseCrudServiceImpl<D,E,ID, R>
        implements BasePagingAndSortingService<D,E,ID,R>  {
}

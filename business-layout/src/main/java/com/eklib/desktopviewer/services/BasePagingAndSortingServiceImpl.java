package com.eklib.desktopviewer.services;

import com.eklib.desktopviewer.dto.BaseDTO;
import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

//todo :  think about the implementation
@Transactional
public abstract class BasePagingAndSortingServiceImpl<D extends BaseDTO, E extends BaseEntity, I extends Serializable, R extends BasePagingAndSortingRepository<E, I>>
        extends BaseCrudServiceImpl<D,E,I, R>
        implements BasePagingAndSortingService<D,E,I,R>  {
}

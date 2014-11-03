package com.eklib.desktopviewer.services;

import com.eklib.desktopviewer.dto.BaseDTO;
import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepository;

import java.io.Serializable;

//todo :  think about the implementation
public interface BasePagingAndSortingService<D extends BaseDTO, E extends BaseEntity, I extends Serializable, R extends BasePagingAndSortingRepository<E, I>>
        extends BaseCrudService<D,E,I,R> {
}

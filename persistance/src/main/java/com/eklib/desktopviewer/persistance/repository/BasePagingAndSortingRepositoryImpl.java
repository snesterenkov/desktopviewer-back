package com.eklib.desktopviewer.persistance.repository;

import com.eklib.desktopviewer.persistance.model.BaseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

// todo: think about the implementation
@Transactional
public abstract class BasePagingAndSortingRepositoryImpl<E extends BaseEntity,  I extends Serializable>
        extends BaseCrudRepositoryImpl<E, I>
        implements BasePagingAndSortingRepository<E,I> {
}

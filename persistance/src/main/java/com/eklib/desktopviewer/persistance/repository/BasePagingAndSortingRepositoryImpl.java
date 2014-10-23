package com.eklib.desktopviewer.persistance.repository;

import com.eklib.desktopviewer.persistance.model.BaseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

// todo: think about the implementation
@Transactional
public abstract class BasePagingAndSortingRepositoryImpl<T extends BaseEntity,  ID extends Serializable> extends BaseCrudRepositoryImpl<T, ID> {
}

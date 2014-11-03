package com.eklib.desktopviewer.services;

import com.eklib.desktopviewer.dto.BaseDTO;
import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.repository.BaseCrudRepository;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

@Transactional
public abstract class BaseCrudServiceImpl<D extends BaseDTO, E extends BaseEntity, I extends Serializable, R extends BaseCrudRepository<E, I>> extends BaseServiceImpl<D, E, I, R> implements BaseCrudService<D, E, I, R> {

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public D insert(D dto) {
        E entity = modelMapper.map(dto, getEntityType());
        E insertedEntity = getRepository().insert(entity);
        return modelMapper.map(insertedEntity, getDTOType());
    }

    @Override
    public D update(I id, D dto) {
        E entity = modelMapper.map(dto, getEntityType());
        E updatedEntity = getRepository().update(entity);
        return modelMapper.map(updatedEntity, getDTOType());
    }

    @Override
    public D findById(I id) {
        E entity = getRepository().findById(id);
        return modelMapper.map(entity, getDTOType());
    }

    @Override
    public Collection<D> findAll() {
        return FluentIterable.from(getRepository().findAll()).transform(new Function<E, D>() {
            @Override
            public D apply(E entity) {
                return modelMapper.map(entity, getDTOType());
            }
        }).toList();
    }

    @Override
    public void delete(I id) {
        E entity = getRepository().findById(id);
        getRepository().delete(entity);
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}

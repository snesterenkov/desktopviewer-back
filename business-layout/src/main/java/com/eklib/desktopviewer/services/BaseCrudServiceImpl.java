package com.eklib.desktopviewer.services;

import com.eklib.desktopviewer.dto.BaseDTO;
import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.repository.BaseCrudRepository;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Transactional
public abstract class BaseCrudServiceImpl<D extends BaseDTO, E extends BaseEntity, ID extends Serializable, R extends BaseCrudRepository<E, ID>> extends BaseServiceImpl<D, E, ID, R> implements BaseCrudService<D, E, ID, R> {

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public D insert(D dto) {
        E entity = modelMapper.map(dto, getEntityType());
        E insertedEntity = getRepository().insert(entity);
        return modelMapper.map(insertedEntity, getDTOType());
    }

    @Override
    public D update(ID id, D dto) {
        E entity = modelMapper.map(dto, getEntityType());
        E updatedEntity = getRepository().update(entity);
        return modelMapper.map(updatedEntity, getDTOType());
    }

    @Override
    public D findById(ID id) {
        E entity = getRepository().findById(id);
        return modelMapper.map(entity, getDTOType());
    }

    @Override
    public Collection<D> findAll() {
        List<E> entities = getRepository().findAll();
        List<D> dtos = new ArrayList<D>(entities.size());
        for(E entity : entities) {
           dtos.add(modelMapper.map(entity, getDTOType()));
        }
        return dtos;
    }

    @Override
    public void delete(ID id) {
        E entity = getRepository().findById(id);
        getRepository().delete(entity);
    }
}

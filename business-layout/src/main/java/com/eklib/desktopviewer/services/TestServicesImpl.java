package com.eklib.desktopviewer.services;

import com.eklib.desktopviewer.dto.TestDTO;
import com.eklib.desktopviewer.persistance.model.TestEntity;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepositoryImpl;
import com.eklib.desktopviewer.persistance.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Maxim on 13.11.2014.
 */
@Service
@Transactional
public class TestServicesImpl extends BasePagingAndSortingServiceImpl<TestDTO, TestEntity, Long, TestRepository>
        implements TestServices {

    @Autowired
    TestRepository testRepository;

    @Override
    public Class<TestEntity> getEntityType() {
        return  TestEntity.class;
    }

    @Override
    public Class<TestDTO> getDTOType() {
        return TestDTO.class;
    }

    @Override
    public TestDTO insert(TestDTO dto) {
        TestEntity testEntity = getModelMapper().map(dto, getEntityType());
        TestEntity newTestEntity = getRepository().insert(testEntity);
        return getModelMapper().map(newTestEntity, getDTOType());
    }
}

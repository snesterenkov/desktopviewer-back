package com.eklib.desktopviewer.services.test;

import com.eklib.desktopviewer.convertor.fromdto.test.TestFromDTO;
import com.eklib.desktopviewer.convertor.todto.test.TestToDTO;
import com.eklib.desktopviewer.dto.test.TestDTO;
import com.eklib.desktopviewer.persistance.model.test.TestEntity;
import com.eklib.desktopviewer.persistance.repository.test.TestRepository;
import com.google.common.collect.FluentIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by Maxim on 13.11.2014.
 */
@Service
@Transactional
public class TestServicesImpl implements TestServices {

    @Autowired
    private TestFromDTO testFromDTO;
    @Autowired
    private TestToDTO testToDTO;
    @Autowired
    TestRepository testRepository;

    @Override
    public TestDTO insert(TestDTO dto) {
        TestEntity testEntity = testFromDTO.apply(dto);
        TestEntity newTestEntity = testRepository.insert(testEntity);
        return testToDTO.apply(newTestEntity);
    }



    @Override
    public TestDTO findById(Long id) {
        return testToDTO.apply(testRepository.findById(id));
    }

    @Override
    public Collection<TestDTO> findAll() {
        return FluentIterable.from(testRepository.findAll()).transform(testToDTO).toList();
    }

}

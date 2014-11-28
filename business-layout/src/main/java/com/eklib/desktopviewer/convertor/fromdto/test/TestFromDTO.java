package com.eklib.desktopviewer.convertor.fromdto.test;

import com.eklib.desktopviewer.dto.test.TestDTO;
import com.eklib.desktopviewer.persistance.model.test.TestEntity;
import com.eklib.desktopviewer.persistance.repository.test.TestRepository;
import com.google.common.base.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vadim on 13.11.2014.
 */
@Component
public class TestFromDTO implements Function<TestDTO, TestEntity> {

    @Autowired
    private TestRepository testRepository;

    @Override
    public TestEntity apply(TestDTO testDTO) {
        TestEntity entity;
        if(testDTO == null){
            return null;
        }
        if(testDTO.getId() == null || testDTO.getId() == 0L){
            entity = new TestEntity();
        } else {
            entity = testRepository.findById(testDTO.getId());
        }
        entity.setNote(testDTO.getNote());

        return entity;
    }
}

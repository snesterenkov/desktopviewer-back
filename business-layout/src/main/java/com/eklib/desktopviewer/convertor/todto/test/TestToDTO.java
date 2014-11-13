package com.eklib.desktopviewer.convertor.todto.test;

import com.eklib.desktopviewer.dto.test.TestDTO;
import com.eklib.desktopviewer.persistance.model.test.TestEntity;
import com.google.common.base.Function;
import org.springframework.stereotype.Component;

/**
 * Created by vadim on 13.11.2014.
 */
@Component
public class TestToDTO implements Function<TestEntity, TestDTO> {

    @Override
    public TestDTO apply(TestEntity testEntity) {
        if(testEntity == null){
            return null;
        }
        TestDTO testDTO = new TestDTO();
        testDTO.setId(testEntity.getId());
        testDTO.setNote(testEntity.getNote());
        return testDTO;
    }
}

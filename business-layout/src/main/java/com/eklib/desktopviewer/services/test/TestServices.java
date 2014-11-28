package com.eklib.desktopviewer.services.test;

import com.eklib.desktopviewer.dto.test.TestDTO;
import com.eklib.desktopviewer.persistance.model.test.TestEntity;
import com.eklib.desktopviewer.services.BaseCrudService;

import java.util.Collection;

/**
 * Created by Maxim on 13.11.2014.
 */
public interface TestServices  {

    TestDTO insert(TestDTO dto);

    TestDTO findById(Long id);

    Collection<TestDTO> findAll();
}

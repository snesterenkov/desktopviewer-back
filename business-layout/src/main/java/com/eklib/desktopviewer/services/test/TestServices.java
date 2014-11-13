package com.eklib.desktopviewer.services.test;

import com.eklib.desktopviewer.dto.test.TestDTO;
import com.eklib.desktopviewer.persistance.model.test.TestEntity;
import com.eklib.desktopviewer.services.BaseCrudService;

/**
 * Created by Maxim on 13.11.2014.
 */
public interface TestServices extends BaseCrudService<TestDTO, TestEntity, Long> {
}

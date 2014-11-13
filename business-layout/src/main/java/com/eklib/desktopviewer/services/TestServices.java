package com.eklib.desktopviewer.services;

import com.eklib.desktopviewer.dto.TestDTO;
import com.eklib.desktopviewer.persistance.model.TestEntity;
import com.eklib.desktopviewer.persistance.repository.TestRepository;

/**
 * Created by Maxim on 13.11.2014.
 */
public interface TestServices extends BaseCrudService<TestDTO, TestEntity, Long, TestRepository> {
}

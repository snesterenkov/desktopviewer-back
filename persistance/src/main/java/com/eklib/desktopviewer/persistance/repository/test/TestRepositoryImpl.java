package com.eklib.desktopviewer.persistance.repository.test;

import com.eklib.desktopviewer.persistance.model.test.TestEntity;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Maxim on 13.11.2014.
 */
@Repository
@Transactional
public class TestRepositoryImpl extends BasePagingAndSortingRepositoryImpl<TestEntity, Long>
    implements TestRepository{
}

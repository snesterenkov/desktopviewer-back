package com.eklib.desktopviewer.persistance.repository.companystructure;

import com.eklib.desktopviewer.persistance.model.companystructure.DepartmentEntity;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Maxim on 10.11.2014.
 */

@Repository
@Transactional
public class DepartmentRepositoryImpl extends BasePagingAndSortingRepositoryImpl<DepartmentEntity, Long>
        implements DepartmentRepository {
}

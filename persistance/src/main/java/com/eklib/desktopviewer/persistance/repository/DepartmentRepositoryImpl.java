package com.eklib.desktopviewer.persistance.repository;

import com.eklib.desktopviewer.persistance.model.Department;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Maxim on 10.11.2014.
 */

@Repository
@Transactional
public class DepartmentRepositoryImpl extends BasePagingAndSortingRepositoryImpl<Department, Long>
        implements DepartmentRepository {
}

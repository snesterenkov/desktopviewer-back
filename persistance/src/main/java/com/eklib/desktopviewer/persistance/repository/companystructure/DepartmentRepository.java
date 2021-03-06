package com.eklib.desktopviewer.persistance.repository.companystructure;

import com.eklib.desktopviewer.persistance.model.companystructure.DepartmentEntity;
import com.eklib.desktopviewer.persistance.model.enums.StatusEnum;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepository;

import java.util.List;

/**
 * Created by Maxim on 10.11.2014.
 */
public interface DepartmentRepository extends BasePagingAndSortingRepository<DepartmentEntity, Long> {

    public boolean changeStatus(DepartmentEntity company, StatusEnum newStatus);

    public List<DepartmentEntity> findByUser(String client);

    boolean hasOpenDepartmentForClient(Long idDepartment, String login);

    List<DepartmentEntity> findOpenByUser(String client);
}

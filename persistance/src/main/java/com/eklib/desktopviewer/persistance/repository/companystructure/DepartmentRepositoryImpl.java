package com.eklib.desktopviewer.persistance.repository.companystructure;

import com.eklib.desktopviewer.persistance.model.companystructure.DepartmentEntity;
import com.eklib.desktopviewer.persistance.model.enums.StatusEnum;
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

    @Override
    public boolean changeStatus(DepartmentEntity department, StatusEnum newStatus) {
        if(!department.getStatus().equals(newStatus)){
            if(newStatus.equals(StatusEnum.OPEN)){
                department.setStatus(newStatus);
                update(department);
                return true;
            } else {
                department.setStatus(newStatus);
                update(department);
                return true;
            }
        }
        return false;
    }
}

package com.eklib.desktopviewer.persistance.repository.companystructure;

import com.eklib.desktopviewer.persistance.model.companystructure.DepartmentEntity;
import com.eklib.desktopviewer.persistance.model.enums.StatusEnum;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepositoryImpl;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public List<DepartmentEntity> findByUser(String client) {
        Criteria criteria = getSession().createCriteria(DepartmentEntity.class);
        criteria.createAlias("owner", "ow", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.eq("ow.login", client));
        return criteria.list();
    }
}

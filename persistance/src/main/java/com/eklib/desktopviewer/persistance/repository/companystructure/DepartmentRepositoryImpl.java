package com.eklib.desktopviewer.persistance.repository.companystructure;

import com.eklib.desktopviewer.persistance.model.companystructure.DepartmentEntity;
import com.eklib.desktopviewer.persistance.model.companystructure.ProjectEntity;
import com.eklib.desktopviewer.persistance.model.enums.StatusEnum;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepositoryImpl;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public boolean changeStatus(DepartmentEntity department, StatusEnum newStatus) {
        if(!department.getStatus().equals(newStatus)){
            if(department.getStatus().equals(StatusEnum.OPEN)){
                for(ProjectEntity project : department.getProjects()){
                    projectRepository.changeStatus(project, newStatus);
                }
                department.setStatus(newStatus);
                update(department);
                return true;
            } else if(department.getStatus().equals(StatusEnum.PAUSED)){
                if(newStatus.equals(StatusEnum.OPEN)){
                    department.setStatus(newStatus);
                    update(department);
                    return true;
                } else {
                    for(ProjectEntity project : department.getProjects()){
                        projectRepository.changeStatus(project, newStatus);
                    }
                    department.setStatus(newStatus);
                    update(department);
                    return true;
                }
            } else if ((department.getStatus().equals(StatusEnum.CLOSED))){
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
        criteria.add(Restrictions.or(Restrictions.eq("ow.login", client),Restrictions.eq("ow.email", client)));
        return criteria.list();
    }

    @Override
    public boolean hasOpenDepartmentForClient(Long idCompany, String login) {
        Criteria criteria = getSession().createCriteria(DepartmentEntity.class);
        criteria.createAlias("owner", "ow", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.eq("ow.login", login));
        criteria.add(Restrictions.eq("id", idCompany));
        criteria.add(Restrictions.eq("status", StatusEnum.OPEN));
        criteria.setProjection(Projections.rowCount());
        return ((Long) criteria.uniqueResult()) > 0;
    }

    @Override
    public List<DepartmentEntity> findOpenByUser(String client) {
        Criteria criteria = getSession().createCriteria(DepartmentEntity.class);
        criteria.createAlias("owner", "ow", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.eq("ow.login", client));
        criteria.add(Restrictions.eq("status", StatusEnum.OPEN));
        return criteria.list();
    }
}

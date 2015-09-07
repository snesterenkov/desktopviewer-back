package com.eklib.desktopviewer.persistance.repository.companystructure;

import com.eklib.desktopviewer.persistance.model.companystructure.ProjectEntity;
import com.eklib.desktopviewer.persistance.model.enums.StatusEnum;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepositoryImpl;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vadim on 01.12.2014.
 */
@Repository
@Transactional
public class ProjectRepositoryImpl extends BasePagingAndSortingRepositoryImpl<ProjectEntity, Long> implements ProjectRepository {

    @Override
    public boolean changeStatus(ProjectEntity project, StatusEnum newStatus) {
        if(!project.getStatus().equals(newStatus)){
            project.setStatus(newStatus);
            update(project);
            return true;
        }
        return false;
    }

    @Override
    public List<ProjectEntity> findByUser(String client) {
        Criteria criteria = getSession().createCriteria(ProjectEntity.class);
        criteria.createAlias("owner", "ow", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.or(Restrictions.eq("ow.login", client),Restrictions.eq("ow.email", client)));
        return criteria.list();
    }

    @Override
    public List<ProjectEntity> findForMember(Long userId, String client) {
        Criteria criteria = getSession().createCriteria(ProjectEntity.class)
                .createAlias("owner", "ow", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.or(Restrictions.eq("ow.login", client), Restrictions.eq("ow.email", client)))
                .createCriteria("userEntities", "users")
                .add(Restrictions.eq("users.id", userId));
        return criteria.list();
    }
}

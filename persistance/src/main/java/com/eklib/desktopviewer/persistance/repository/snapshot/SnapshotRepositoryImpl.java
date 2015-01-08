package com.eklib.desktopviewer.persistance.repository.snapshot;

import com.eklib.desktopviewer.persistance.model.snapshot.SnapshotEntity;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepositoryImpl;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vadim on 18.12.2014.
 */
@Repository
@Transactional
public class SnapshotRepositoryImpl extends BasePagingAndSortingRepositoryImpl<SnapshotEntity, Long> implements SnapshotRepository {

    @Override
    public List<SnapshotEntity> findByUserName(String client) {
        Criteria criteria = getSession().createCriteria(SnapshotEntity.class);
        criteria.createAlias("user", "ow", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.or(Restrictions.eq("ow.login", client), Restrictions.eq("ow.email", client)));
        return criteria.list();
    }

    @Override
    public List<SnapshotEntity> findByUserId(Long userId){
        Criteria criteria = getSession().createCriteria(SnapshotEntity.class);
        criteria.createAlias("user", "ow", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.eq("ow.id", userId));
        return criteria.list();
    }
}

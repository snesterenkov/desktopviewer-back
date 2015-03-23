package com.eklib.desktopviewer.persistance.repository.snapshot;

import com.eklib.desktopviewer.persistance.model.snapshot.SnapshotEntity;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepositoryImpl;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by vadim on 18.12.2014.
 */
@Repository
@Transactional
public class SnapshotRepositoryImpl extends BasePagingAndSortingRepositoryImpl<SnapshotEntity, Long> implements SnapshotRepository {

    @Override
    public List<SnapshotEntity> findByUserName(String client) {
        Criteria criteria = getSession().createCriteria(SnapshotEntity.class);
        criteria.createAlias("user", "ow", JoinType.INNER_JOIN);
        criteria.add(Restrictions.or(Restrictions.eq("ow.login", client), Restrictions.eq("ow.email", client)));
        return criteria.list();
    }

    @Override
    public List<SnapshotEntity> findByUserId(Long userId){
        Criteria criteria = getSession().createCriteria(SnapshotEntity.class);
        criteria.createAlias("user", "ow", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("ow.id", userId));
        return criteria.list();
    }

    @Override
    public List<SnapshotEntity> findByUserIdAndDate(Long userId, Date date){
        Criteria criteria = getSession().createCriteria(SnapshotEntity.class);
        criteria.createAlias("user", "ow", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("ow.id", userId));

        Date maxDate = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));

        Conjunction conj = Restrictions.conjunction();
        conj.add(Restrictions.ge("date", date));
        conj.add(Restrictions.le("date", maxDate));
        criteria.add(conj);

        return criteria.list();
    }
}

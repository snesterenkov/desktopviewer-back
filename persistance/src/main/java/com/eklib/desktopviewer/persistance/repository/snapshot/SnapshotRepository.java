package com.eklib.desktopviewer.persistance.repository.snapshot;

import com.eklib.desktopviewer.persistance.model.snapshot.SnapshotEntity;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by vadim on 18.12.2014.
 */
public interface SnapshotRepository extends BasePagingAndSortingRepository<SnapshotEntity, Long> {

    List<SnapshotEntity> findByUserName(String client);

    List<SnapshotEntity> findByUserId(Long userId);

    List<SnapshotEntity> findByUserIdAndDate(Long userId, Date date);

    List<Object[]> getUsersStatsByDate(Date date, String client);

    Integer countByUserIdAndProjectIdAndPeriod(List<Long> projectIds, Long userId, Date startDate, Date endDate);
}

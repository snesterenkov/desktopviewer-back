package com.eklib.desktopviewer.persistance.repository.snapshot;

import com.eklib.desktopviewer.persistance.model.snapshot.SnapshotEntity;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepository;

import java.util.List;

/**
 * Created by vadim on 18.12.2014.
 */
public interface SnapshotRepository extends BasePagingAndSortingRepository<SnapshotEntity, Long> {

    List<SnapshotEntity> findByUserName(String client);
}
package com.eklib.desktopviewer.services.snapshot;

import com.eklib.desktopviewer.dto.snapshot.SnapshotDTO;

import java.util.Date;
import java.util.List;

/**
 * Created by vadim on 18.12.2014.
 */
public interface SnapshotService {

    SnapshotDTO insert(SnapshotDTO companyDTO, String client);

    List<String> getFileName(String client);

    /**
     * Find all snapshots with given user id
     *
     * @param userId  - user id
     * @return   list of SnapshotDTO
     */
    List<SnapshotDTO> findSnapshotsByUser(Long userId, String client);

    List<SnapshotDTO> findSnapshotsByUserAndDate(Long userId, Date date, String client);

    SnapshotDTO findById(Long id);
}

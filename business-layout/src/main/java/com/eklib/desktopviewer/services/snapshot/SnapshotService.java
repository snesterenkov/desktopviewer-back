package com.eklib.desktopviewer.services.snapshot;

import com.eklib.desktopviewer.dto.snapshot.FullSnapshotDTO;
import com.eklib.desktopviewer.dto.snapshot.SnapshotDTO;
import com.eklib.desktopviewer.dto.snapshot.UserStatsDTO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by vadim on 18.12.2014.
 */
public interface SnapshotService {

    SnapshotDTO insert(SnapshotDTO companyDTO, Date date, String client);

    List<String> getFileName(String client);

    /**
     * Find all snapshots with given user id
     *
     * @param userId  - user id
     * @return   list of SnapshotDTO
     */
    List<SnapshotDTO> findSnapshotsByUser(Long userId, String client);

    List<SnapshotDTO> findSnapshotsByUserAndDate(Long userId, Date date, String client);

    FullSnapshotDTO findById(Long id);

    Map<Long, UserStatsDTO> getUsersStatsByDate(Date date, String client);

    List<Integer> calculateCountScreenshotsOnDayByMonth(Long userId, Date date);
}

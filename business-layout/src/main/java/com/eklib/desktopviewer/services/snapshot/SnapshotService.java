package com.eklib.desktopviewer.services.snapshot;

import com.eklib.desktopviewer.dto.snapshot.SnapshotDTO;

import java.util.List;

/**
 * Created by vadim on 18.12.2014.
 */
public interface SnapshotService {

    SnapshotDTO insert(SnapshotDTO companyDTO, String client);

    List<String> getFileName(String client);

    List<SnapshotDTO> findByUser(Long userId);
}

package com.eklib.desktopviewer.convertor.todto.snapshot;

import com.eklib.desktopviewer.dto.snapshot.FullSnapshotDTO;
import com.eklib.desktopviewer.persistance.model.snapshot.SnapshotEntity;
import com.google.common.base.Function;
import org.springframework.stereotype.Component;

/**
 * Created by alex on 8/27/2015.
 */
@Component
public class FullSnapshotToDTO implements Function<SnapshotEntity, FullSnapshotDTO> {

    @Override
    public FullSnapshotDTO apply(SnapshotEntity snapshot) {
        if(snapshot == null){
            return null;
        }
        FullSnapshotDTO snapshotDTO = new FullSnapshotDTO();
        snapshotDTO.setId(snapshot.getId());
        snapshotDTO.setMessage(snapshot.getMessage());
        snapshotDTO.setNote(snapshot.getNote());
        snapshotDTO.setProgectId(snapshot.getProject().getId());
        snapshotDTO.setFileName(snapshot.getFilename());
        snapshotDTO.setCountMouseClick(snapshot.getCountMouseClick());
        snapshotDTO.setCountKeyboardClick(snapshot.getCountKeyboardClick());
        snapshotDTO.setTimeInterval(snapshot.getTimeInterval());
        snapshotDTO.setDate(snapshot.getDate());
        snapshotDTO.setTime(snapshot.getDate());

        return snapshotDTO;
    }
}

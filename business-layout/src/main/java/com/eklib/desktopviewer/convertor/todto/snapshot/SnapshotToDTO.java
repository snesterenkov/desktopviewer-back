package com.eklib.desktopviewer.convertor.todto.snapshot;


import com.eklib.desktopviewer.dto.snapshot.SnapshotDTO;
import com.eklib.desktopviewer.persistance.model.snapshot.SnapshotEntity;
import com.google.common.base.Function;
import org.springframework.stereotype.Component;

/**
 * Created by alex on 06.01.2015.
 */
@Component
public class SnapshotToDTO implements Function<SnapshotEntity, SnapshotDTO> {

    @Override
    public SnapshotDTO apply(SnapshotEntity snapshot) {
        if(snapshot == null){
            return null;
        }
        SnapshotDTO snapshotDTO = new SnapshotDTO();
        snapshotDTO.setId(snapshot.getId());
        snapshotDTO.setMessage(snapshot.getMessage());
        snapshotDTO.setNote(snapshot.getNote());
        snapshotDTO.setProgectId(snapshot.getProject().getId());
        snapshotDTO.setFileName(snapshot.getFilename());
        snapshotDTO.setCountMouseClick(snapshot.getCountMouseClick());
        snapshotDTO.setCountKeyboardClick(snapshot.getCountKeyboardClick());
        snapshotDTO.setTimeInterval(snapshot.getTimeInterval());
        snapshotDTO.setDate(snapshot.getDate());

        return snapshotDTO;
    }
}

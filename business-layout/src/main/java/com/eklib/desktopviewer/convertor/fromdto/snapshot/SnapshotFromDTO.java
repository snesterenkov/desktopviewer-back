package com.eklib.desktopviewer.convertor.fromdto.snapshot;

import com.eklib.desktopviewer.dto.snapshot.SnapshotDTO;
import com.eklib.desktopviewer.persistance.model.snapshot.SnapshotEntity;
import com.eklib.desktopviewer.persistance.repository.companystructure.ProjectRepository;
import com.eklib.desktopviewer.persistance.repository.snapshot.SnapshotRepository;
import com.google.common.base.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vadim on 18.12.2014.
 */
@Component
public class SnapshotFromDTO implements Function<SnapshotDTO, SnapshotEntity> {

    @Autowired
    private SnapshotRepository repository;
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public SnapshotEntity apply(SnapshotDTO dto) {
        SnapshotEntity entity;
        if(dto == null){
            return null;
        }
        if(dto.getId() == null || dto.getId() == 0L){
            entity = new SnapshotEntity();
        } else {
            entity = repository.findById(dto.getId());
        }
        entity.setId(dto.getId());
        entity.setNote(dto.getNote());
        entity.setMessage(dto.getMessage());
        entity.setProject(projectRepository.findById(dto.getProgectId()));
        entity.setCountKeyboardClick(dto.getCountKeyboardClick());
        entity.setCountMouseClick(dto.getCountMouseClick());
        entity.setTimeInterval(dto.getTimeInterval());
        return entity;
    }
}

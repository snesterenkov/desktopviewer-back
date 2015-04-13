package com.eklib.desktopviewer.convertor.todto.companystructure;

import com.eklib.desktopviewer.dto.companystructure.ProjectExtendDTO;
import com.eklib.desktopviewer.dto.enums.StatusDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.ProjectEntity;
import com.google.common.base.Function;
import org.springframework.stereotype.Component;

/**
 * Created by human on 10.04.2015.
 */
@Component
public class ProjectToExtendDTO implements Function<ProjectEntity, ProjectExtendDTO> {

    @Override
    public ProjectExtendDTO apply(ProjectEntity projectEntity) {
        if(projectEntity == null){
            return null;
        }
        ProjectExtendDTO projectExtendDTO = new ProjectExtendDTO();
        projectExtendDTO.setId(projectEntity.getId());
        projectExtendDTO.setName(projectEntity.getName());
        projectExtendDTO.setStatus(StatusDTO.valueOf(projectEntity.getStatus().name()));
        if(projectEntity.getDepartment() != null){
            projectExtendDTO.setDepartmentId(projectEntity.getDepartment().getId());
        }
        return projectExtendDTO;
    }
}

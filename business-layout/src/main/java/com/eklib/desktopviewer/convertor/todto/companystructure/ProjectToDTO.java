package com.eklib.desktopviewer.convertor.todto.companystructure;

import com.eklib.desktopviewer.dto.companystructure.ProjectDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.ProjectEntity;
import com.google.common.base.Function;
import org.springframework.stereotype.Component;

/**
 * Created by vadim on 01.12.2014.
 */
@Component
public class ProjectToDTO implements Function<ProjectEntity, ProjectDTO> {

    @Override
    public ProjectDTO apply(ProjectEntity project) {
        if(project == null){
            return null;
        }
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        if(project.getDepartment() != null){
            projectDTO.setDepartmentId(project.getDepartment().getId());
        }
        return projectDTO;
    }
}

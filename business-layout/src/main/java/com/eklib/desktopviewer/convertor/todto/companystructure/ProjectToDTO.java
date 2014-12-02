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
        ProjectDTO departmentDTO = new ProjectDTO();
        departmentDTO.setId(project.getId());
        departmentDTO.setName(project.getName());
        if(project.getDepartment() != null){
            departmentDTO.setDepartmentId(project.getDepartment().getId());
        }
        return departmentDTO;
    }
}

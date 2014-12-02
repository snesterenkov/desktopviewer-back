package com.eklib.desktopviewer.convertor.todto.companystructure;

import com.eklib.desktopviewer.dto.companystructure.ProjectDetailDTO;
import com.eklib.desktopviewer.dto.enums.StatusDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.ProjectEntity;
import com.google.common.base.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vadim on 01.12.2014.
 */
@Component
public class ProjectToDetailDTO implements Function<ProjectEntity, ProjectDetailDTO> {

    @Autowired
    private DepartmentToDTO departmentToDTO;

    @Override
    public ProjectDetailDTO apply(ProjectEntity project) {
        if(project == null){
            return null;
        }
        ProjectDetailDTO projectDetailDTO = new ProjectDetailDTO();
        projectDetailDTO.setId(project.getId());
        projectDetailDTO.setName(project.getName());
        if(project.getDepartment() != null){
            projectDetailDTO.setDepartmentId(project.getDepartment().getId());
            projectDetailDTO.setDepartmentDTO(departmentToDTO.apply(project.getDepartment()));
        }
        projectDetailDTO.setStatus(StatusDTO.valueOf(project.getStatus().name()));
        return projectDetailDTO;
    }
}

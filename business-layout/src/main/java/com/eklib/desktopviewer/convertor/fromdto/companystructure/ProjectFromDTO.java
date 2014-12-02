package com.eklib.desktopviewer.convertor.fromdto.companystructure;

import com.eklib.desktopviewer.dto.companystructure.ProjectDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.ProjectEntity;
import com.eklib.desktopviewer.persistance.repository.companystructure.DepartmentRepository;
import com.eklib.desktopviewer.persistance.repository.companystructure.ProjectRepository;
import com.google.common.base.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vadim on 01.12.2014.
 */
@Component
public class ProjectFromDTO implements Function<ProjectDTO, ProjectEntity> {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public ProjectEntity apply(ProjectDTO projectDTO) {
        ProjectEntity project;
        if(projectDTO == null){
            return null;
        }
        if(projectDTO.getId() == null || projectDTO.getId() == 0L){
            project = new ProjectEntity();
        } else {
            project = projectRepository.findById(projectDTO.getId());
        }
        project.setName(projectDTO.getName());
        project.setDepartment(departmentRepository.findById(projectDTO.getDepartmentId()));
        return project;
    }
}

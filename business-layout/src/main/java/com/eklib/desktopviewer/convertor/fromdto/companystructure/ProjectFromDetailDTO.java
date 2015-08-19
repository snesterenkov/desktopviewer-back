package com.eklib.desktopviewer.convertor.fromdto.companystructure;

import com.eklib.desktopviewer.convertor.fromdto.security.UserFromDTO;
import com.eklib.desktopviewer.dto.companystructure.ProjectDetailDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.ProjectEntity;
import com.eklib.desktopviewer.persistance.repository.companystructure.DepartmentRepository;
import com.eklib.desktopviewer.persistance.repository.companystructure.ProjectRepository;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by s.sheman on 19.08.2015.
 */
@Component
public class ProjectFromDetailDTO implements Function<ProjectDetailDTO, ProjectEntity> {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserFromDTO userFromDTO;

    @Override
    public ProjectEntity apply(ProjectDetailDTO projectDetailDTO) {
        ProjectEntity project;
        if(projectDetailDTO == null){
            return null;
        }
        if(projectDetailDTO.getId() == null || projectDetailDTO.getId() == 0L){
            project = new ProjectEntity();
        } else {
            project = projectRepository.findById(projectDetailDTO.getId());
        }
        project.setName(projectDetailDTO.getName());
        project.setDepartment(departmentRepository.findById(projectDetailDTO.getDepartmentId()));
        project.setUserEntities(FluentIterable.from(projectDetailDTO.getUserDTOs()).transform(userFromDTO).toSet());
        return project;
    }
}

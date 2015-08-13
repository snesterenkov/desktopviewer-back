package com.eklib.desktopviewer.convertor.todto.companystructure;

import com.eklib.desktopviewer.convertor.todto.security.UserToDTO;
import com.eklib.desktopviewer.dto.companystructure.ProjectDetailDTO;
import com.eklib.desktopviewer.dto.enums.StatusDTO;
import com.eklib.desktopviewer.dto.security.UserDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.ProjectEntity;
import com.eklib.desktopviewer.persistance.model.security.UserEntity;
import com.google.common.base.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by vadim on 01.12.2014.
 */
@Component
public class ProjectToDetailDTO implements Function<ProjectEntity, ProjectDetailDTO> {

    @Autowired
    private DepartmentToDTO departmentToDTO;

    @Autowired
    private UserToDTO userToDTO;

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
            projectDetailDTO.setParentStatus(StatusDTO.valueOf(project.getDepartment().getStatus().name()));
        }
        projectDetailDTO.setStatus(StatusDTO.valueOf(project.getStatus().name()));
        Set<UserEntity> users = project.getUserEntities();
        if(users != null){
            List<UserDTO> userDTOs = new ArrayList<UserDTO>();
            for(UserEntity user:users){
                userDTOs.add(userToDTO.apply(user));
            }
            projectDetailDTO.setUserDTOs(userDTOs);
        }
        return projectDetailDTO;
    }
}

package com.eklib.desktopviewer.services.personaldata;

import com.eklib.desktopviewer.convertor.todto.companystructure.CompanyToDTO;
import com.eklib.desktopviewer.convertor.todto.companystructure.DepartmentToDTO;
import com.eklib.desktopviewer.convertor.todto.companystructure.ProjectToDTO;
import com.eklib.desktopviewer.dto.companystructure.ProjectDTO;
import com.eklib.desktopviewer.dto.personaldata.UserProjectDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.ProjectEntity;
import com.eklib.desktopviewer.persistance.repository.companystructure.CompanyRepository;
import com.eklib.desktopviewer.persistance.repository.companystructure.DepartmentRepository;
import com.eklib.desktopviewer.persistance.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by human on 07.04.2015.
 */
@Service
@Transactional
public class UserProjectsServiceImpl implements UserProjectsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectToDTO projectToDTO;
    @Autowired
    private CompanyToDTO companyToDTO;
    @Autowired
    private DepartmentToDTO departmentToDTO;

    public List<UserProjectDTO> getUserProjects(String client){
        List<UserProjectDTO> userProjectDTOs = new ArrayList<>();
        UserProjectDTO tempUserProjectDto = new UserProjectDTO();
        List<ProjectEntity> projectEntities = userRepository.getUserByName(client).getProjectEntities();
        for(ProjectEntity projectEntity:projectEntities){
            tempUserProjectDto.setProjectDTO(projectToDTO.apply(projectEntity));
            tempUserProjectDto.setDepartmentDTO(departmentToDTO.apply(projectEntity.getDepartment()));
            tempUserProjectDto.setCompanyDTO(companyToDTO.apply(projectEntity.getDepartment().getCompany()));
            userProjectDTOs.add(tempUserProjectDto);
        }
        return userProjectDTOs;
    }

}

package com.eklib.desktopviewer.services.personaldata;

import com.eklib.desktopviewer.convertor.todto.companystructure.*;
import com.eklib.desktopviewer.dto.personaldata.UserProjectDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.CompanyEntity;
import com.eklib.desktopviewer.persistance.model.companystructure.DepartmentEntity;
import com.eklib.desktopviewer.persistance.model.companystructure.ProjectEntity;
import com.eklib.desktopviewer.persistance.model.security.UserEntity;
import com.eklib.desktopviewer.persistance.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private ProjectToDetailDTO projectToDetailDTO;
    @Autowired
    private CompanyToDTO companyToDTO;
    @Autowired
    private CompanyToDelatilDTO companyToDelatilDTO;
    @Autowired
    private DepartmentToDTO departmentToDTO;
    @Autowired
    private DepartmentToDetailDTO departmentToDetailDTO;

    public List<UserProjectDTO> getUserProjects(String client){
        List<UserProjectDTO> userProjectDTOs = new ArrayList<>();
        List<CompanyEntity> ownerCompanies = userRepository.getUserByName(client).getOwnerCompanies();
        Set<ProjectEntity> projectEntities = userRepository.getUserByName(client).getProjectEntities();
        for (ProjectEntity projectEntity:projectEntities){
            boolean isOwner = false;
            DepartmentEntity userDepartmentEntiy = projectEntity.getDepartment();
            CompanyEntity userCompanyEntity = projectEntity.getDepartment().getCompany();
            if(ownerCompanies.contains(userCompanyEntity))
                isOwner = true;
            userProjectDTOs.add(buildUserProjectDTO(projectEntity, userDepartmentEntiy, userCompanyEntity, isOwner));
        }

        return userProjectDTOs;
    }

    private UserProjectDTO buildUserProjectDTO(ProjectEntity projectEntity, DepartmentEntity departmentEntity, CompanyEntity companyEntity, boolean isOwner){
        UserProjectDTO userProjectDTO = new UserProjectDTO();
        userProjectDTO.setProjectDTO(projectToDTO.apply(projectEntity));
        userProjectDTO.setDepartmentDTO(departmentToDTO.apply(departmentEntity));
        userProjectDTO.setCompanyDTO(companyToDTO.apply(companyEntity));
        userProjectDTO.setIsOwner(isOwner);
        return userProjectDTO;
    }


}

package com.eklib.desktopviewer.services.personaldata;

import com.eklib.desktopviewer.convertor.todto.companystructure.CompaniesProjectsDepartmentsToExtendDTO;
import com.eklib.desktopviewer.dto.companystructure.CompaniesProjectsDepartmentsExtendDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.CompanyEntity;
import com.eklib.desktopviewer.persistance.model.companystructure.DepartmentEntity;
import com.eklib.desktopviewer.persistance.model.companystructure.ProjectEntity;
import com.eklib.desktopviewer.persistance.repository.companystructure.CompanyRepository;
import com.eklib.desktopviewer.persistance.repository.companystructure.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by human on 13.04.2015.
 */
@Service
@Transactional
public class UserProjectsServiceImpl implements UserProjectsService{

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CompaniesProjectsDepartmentsToExtendDTO companiesProjectsDepartmentsToDTO;

    @Override
    public CompaniesProjectsDepartmentsExtendDTO getUserProjects(String client) {
        List<DepartmentEntity> departmentEntities = new ArrayList<>();
        List<ProjectEntity> projectEntities = new ArrayList<>();
        List<CompanyEntity> companyEntities = companyRepository.findOpenByUserHasProjects(client);
        List<ProjectEntity> clientProjects = projectRepository.findByOwner(client);
        List<Boolean> isProjectOwner = new ArrayList<>();
        for (CompanyEntity companyEntity: companyEntities){
            departmentEntities.addAll(companyEntity.getDepartments());
            for(DepartmentEntity departmentEntity:companyEntity.getDepartments()){
                projectEntities.addAll(departmentEntity.getProjects());
            }

        }
        for(ProjectEntity projectEntity: projectEntities){
            isProjectOwner.add(clientProjects.contains(projectEntity));
        }
        return companiesProjectsDepartmentsToDTO.apply(companyEntities, projectEntities, departmentEntities, isProjectOwner);
    }
}

package com.eklib.desktopviewer.convertor.todto.companystructure;

import com.eklib.desktopviewer.dto.companystructure.CompaniesProjectsDepartmentsExtendDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.CompanyEntity;
import com.eklib.desktopviewer.persistance.model.companystructure.DepartmentEntity;
import com.eklib.desktopviewer.persistance.model.companystructure.ProjectEntity;
import com.google.common.collect.FluentIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by human on 16.04.2015.
 */
@Component
public class CompaniesProjectsDepartmentsToExtendDTO {

    @Autowired
    private DepartmentToDetailDTO departmentToDTO;
    @Autowired
    private ProjectToDetailDTO projectToDTO;
    @Autowired
    private CompanyToDetailDTO companyToDTO;

    public CompaniesProjectsDepartmentsExtendDTO apply(List<CompanyEntity> companyEntities, List<ProjectEntity> projectEntities,
                                                 List<DepartmentEntity> departmentEntities, List<Boolean> isProjectOwner) {

        CompaniesProjectsDepartmentsExtendDTO companyProjectsDepartmentsDTO = new CompaniesProjectsDepartmentsExtendDTO();
        companyProjectsDepartmentsDTO.setCompaniesDetailsDTO(FluentIterable.from(companyEntities).transform(companyToDTO).toList());
        companyProjectsDepartmentsDTO.setIsProjectOwner(isProjectOwner);
        companyProjectsDepartmentsDTO.setProjectDTOs(FluentIterable.from(projectEntities).transform(projectToDTO).toList());
        companyProjectsDepartmentsDTO.setDepartmentDetailsDTOs(FluentIterable.from(departmentEntities).transform(departmentToDTO).toList());
        return companyProjectsDepartmentsDTO;
    }

}

package com.eklib.desktopviewer.convertor.todto.companystructure;


import com.eklib.desktopviewer.dto.companystructure.CompaniesProjectsDepartmentsDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.CompanyEntity;
import com.eklib.desktopviewer.persistance.model.companystructure.DepartmentEntity;
import com.eklib.desktopviewer.persistance.model.companystructure.ProjectEntity;
import com.google.common.collect.FluentIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by alex on 4/10/2015.
 */
@Component
public class CompaniesProjectsDepartmentsToDTO {

    @Autowired
    private DepartmentToDetailDTO departmentToDTO;
    @Autowired
    private ProjectToDetailDTO projectToDTO;
    @Autowired
    private CompanyToDelatilDTO companyToDTO;


    public CompaniesProjectsDepartmentsDTO apply(List<CompanyEntity> companyEntities, List<ProjectEntity> projectEntities,
                                               List<DepartmentEntity> departmentEntities) {

        CompaniesProjectsDepartmentsDTO companyProjectsDepartmentsDTO = new CompaniesProjectsDepartmentsDTO();
        companyProjectsDepartmentsDTO.setCompaniesDetailsDTO(FluentIterable.from(companyEntities).transform(companyToDTO).toList());
        companyProjectsDepartmentsDTO.setProjectDetailDTOs(FluentIterable.from(projectEntities).transform(projectToDTO).toList());
        companyProjectsDepartmentsDTO.setDepartmentDetailsDTOs(FluentIterable.from(departmentEntities).transform(departmentToDTO).toList());
        return companyProjectsDepartmentsDTO;
    }
}

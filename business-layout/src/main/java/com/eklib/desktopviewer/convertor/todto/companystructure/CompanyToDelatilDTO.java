package com.eklib.desktopviewer.convertor.todto.companystructure;

import com.eklib.desktopviewer.dto.companystructure.CompanyDTO;
import com.eklib.desktopviewer.dto.companystructure.CompanyDetailDTO;
import com.eklib.desktopviewer.dto.companystructure.DepartmentDTO;
import com.eklib.desktopviewer.dto.enums.StatusDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.CompanyEntity;
import com.eklib.desktopviewer.persistance.model.companystructure.DepartmentEntity;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vadim on 27.11.2014.
 */
@Component
public class CompanyToDelatilDTO implements Function<CompanyEntity,CompanyDetailDTO> {

    @Autowired
    private DepartmentToDTO departmentToDTO;

    @Override
    public CompanyDetailDTO apply(CompanyEntity company) {
        if(company == null){
            return null;
        }
        CompanyDetailDTO companyDTO = new CompanyDetailDTO();
        companyDTO.setId(company.getId());
        companyDTO.setName(company.getName());
        companyDTO.setStatus(StatusDTO.valueOf(company.getStatus().name()));
        companyDTO.setDepartmentsDTO(FluentIterable.from(company.getDepartments()).transform(departmentToDTO).toList());
        return companyDTO;
    }
}

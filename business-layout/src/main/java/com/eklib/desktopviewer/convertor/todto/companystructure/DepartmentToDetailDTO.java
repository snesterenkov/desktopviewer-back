package com.eklib.desktopviewer.convertor.todto.companystructure;

import com.eklib.desktopviewer.dto.companystructure.DepartmentDetailDTO;
import com.eklib.desktopviewer.dto.enums.StatusDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.DepartmentEntity;
import com.google.common.base.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vadim on 01.12.2014.
 */
@Component
public class DepartmentToDetailDTO implements Function<DepartmentEntity,DepartmentDetailDTO> {

    @Autowired
    private CompanyToDTO companyToDTO;

    @Override
    public DepartmentDetailDTO apply(DepartmentEntity department) {
        if(department == null){
            return null;
        }
        DepartmentDetailDTO departmentDTO = new DepartmentDetailDTO();
        departmentDTO.setId(department.getId());
        departmentDTO.setName(department.getName());
        if(department.getCompany() != null){
            departmentDTO.setCompanyId(department.getCompany().getId());
            departmentDTO.setCompanyDTO(companyToDTO.apply(department.getCompany()));
            departmentDTO.setParentStatus(StatusDTO.valueOf(department.getCompany().getStatus().name()));
        }
        departmentDTO.setStatus(StatusDTO.valueOf(department.getStatus().name()));
        return departmentDTO;
    }
}

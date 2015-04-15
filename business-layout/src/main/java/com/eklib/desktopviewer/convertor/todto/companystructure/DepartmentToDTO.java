package com.eklib.desktopviewer.convertor.todto.companystructure;

import com.eklib.desktopviewer.dto.companystructure.DepartmentDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.DepartmentEntity;
import com.google.common.base.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vadim on 13.11.2014.
 */
@Component
public class DepartmentToDTO implements Function<DepartmentEntity, DepartmentDTO> {

    @Autowired
    private ProjectToDTO projectToDTO;

    @Override
    public DepartmentDTO apply(DepartmentEntity department) {
        if(department == null){
            return null;
        }
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(department.getId());
        departmentDTO.setName(department.getName());
        if(department.getCompany() != null){
            departmentDTO.setCompanyId(department.getCompany().getId());
        }
        return departmentDTO;
    }
}

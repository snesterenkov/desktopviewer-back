package com.eklib.desktopviewer.convertor.todto.companystructure;

import com.eklib.desktopviewer.dto.companystructure.DepartmentExtendDTO;
import com.eklib.desktopviewer.dto.enums.StatusDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.DepartmentEntity;
import com.google.common.base.Function;
import org.springframework.stereotype.Component;

/**
 * Created by human on 10.04.2015.
 */
@Component
public class DepartmentToExtendDTO implements Function<DepartmentEntity, DepartmentExtendDTO> {

    @Override
    public DepartmentExtendDTO apply(DepartmentEntity departmentEntity) {
        if(departmentEntity == null){
            return null;
        }
        DepartmentExtendDTO departmentExtendDTO = new DepartmentExtendDTO();
        departmentExtendDTO.setId(departmentEntity.getId());
        departmentExtendDTO.setName(departmentEntity.getName());
        departmentExtendDTO.setStatus(StatusDTO.valueOf(departmentEntity.getStatus().name()));
        if(departmentEntity.getCompany() != null){
            departmentExtendDTO.setCompanyid(departmentEntity.getCompany().getId());
        }
        return departmentExtendDTO;
    }
}

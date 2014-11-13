package com.eklib.desktopviewer.convertor.fromdto.companystructure;

import com.eklib.desktopviewer.dto.companystructure.DepartmentDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.DepartmentEntity;
import com.eklib.desktopviewer.persistance.repository.companystructure.CompanyRepository;
import com.eklib.desktopviewer.persistance.repository.companystructure.DepartmentRepository;
import com.google.common.base.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vadim on 13.11.2014.
 */
@Component
public class DepartmentFromDTO implements Function<DepartmentDTO, DepartmentEntity> {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public DepartmentEntity apply(DepartmentDTO departmentDTO) {
        DepartmentEntity department;
        if(departmentDTO == null){
            return null;
        }
        if(departmentDTO.getId() == null || departmentDTO.getId() == 0l){
            department = new DepartmentEntity();
        } else {
            department = departmentRepository.findById(departmentDTO.getId());
        }
        department.setName(departmentDTO.getName());
        department.setCompany(companyRepository.findById(departmentDTO.getCompanyid()));

        return department;
    }
}

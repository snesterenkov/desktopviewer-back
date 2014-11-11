package com.eklib.desktopviewer.services.department;

import com.eklib.desktopviewer.dto.DepartmentDTO;
import com.eklib.desktopviewer.dto.UserDTO;
import com.eklib.desktopviewer.dto.util.RolesConverter;
import com.eklib.desktopviewer.persistance.model.Company;
import com.eklib.desktopviewer.persistance.model.Department;
import com.eklib.desktopviewer.persistance.model.User;
import com.eklib.desktopviewer.persistance.repository.CompanyRepository;
import com.eklib.desktopviewer.persistance.repository.DepartmentRepository;
import com.eklib.desktopviewer.services.BasePagingAndSortingServiceImpl;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Maxim on 10.11.2014.
 */
@Service
@Transactional
public class DepartmentServiceImpl extends BasePagingAndSortingServiceImpl<DepartmentDTO, Department, Long, DepartmentRepository>
        implements DepartmentService {

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public Class<DepartmentDTO> getDTOType() {
        return DepartmentDTO.class;
    }

    @Override
    public Class<Department> getEntityType() {
        return Department.class;
    }

    @Override
    public DepartmentDTO insert(DepartmentDTO departmentDTO) {
        Assert.hasLength(departmentDTO.getName(), "Department name must not be null and not the empty String.");
        Assert.notNull(departmentDTO.getCompanyDTO());
        Department entity = getModelMapper().map(departmentDTO, getEntityType());
        Department newDepartment =  getRepository().insert(entity);
        return getModelMapper().map(newDepartment, getDTOType());
    }

    @Override
    public DepartmentDTO update(Long id, DepartmentDTO departmentDTO) {
        Assert.hasLength(departmentDTO.getName(), "Department name must not be null and not the empty String.");
        Company company = companyRepository.findById(departmentDTO.getCompanyDTO().getId());
        Assert.notNull(company, "Department related to not existing Company");
        Department newDepartment = getModelMapper().map(departmentDTO, getEntityType());
        newDepartment.setId(id);
        newDepartment.setCompany(company);
        Department updatedDepartment = getRepository().merge(newDepartment);
        return getModelMapper().map(updatedDepartment, getDTOType());
    }
}

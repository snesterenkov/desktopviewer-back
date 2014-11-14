package com.eklib.desktopviewer.services.companystructure;

import com.eklib.desktopviewer.convertor.fromdto.companystructure.DepartmentFromDTO;
import com.eklib.desktopviewer.convertor.todto.companystructure.DepartmentToDTO;
import com.eklib.desktopviewer.dto.companystructure.DepartmentDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.CompanyEntity;
import com.eklib.desktopviewer.persistance.model.companystructure.DepartmentEntity;
import com.eklib.desktopviewer.persistance.repository.companystructure.CompanyRepository;
import com.eklib.desktopviewer.persistance.repository.companystructure.DepartmentRepository;
import com.google.common.collect.FluentIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * Created by Maxim on 10.11.2014.
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentFromDTO departmentFromDTO;
    @Autowired
    private DepartmentToDTO departmentToDTO;

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public DepartmentDTO insert(DepartmentDTO departmentDTO) {
        Assert.hasLength(departmentDTO.getName(), "Department name must not be null and not the empty String.");
        CompanyEntity company = companyRepository.findById(departmentDTO.getCompanyid());
        Assert.notNull(company, "Department related to not existing Company");
        DepartmentEntity entity = departmentFromDTO.apply(departmentDTO);
        DepartmentEntity newDepartment =  departmentRepository.insert(entity);
        return departmentToDTO.apply(newDepartment);
    }

    @Override
    public DepartmentDTO update(Long id, DepartmentDTO departmentDTO) {
        Assert.hasLength(departmentDTO.getName(), "Department name must not be null and not the empty String.");
        CompanyEntity company = companyRepository.findById(departmentDTO.getCompanyid());
        Assert.notNull(company, "Department related to not existing Company");
        DepartmentEntity entity = departmentFromDTO.apply(departmentDTO);
        DepartmentEntity updatedDepartment = departmentRepository.update(entity);
        return departmentToDTO.apply(updatedDepartment);
    }

    @Override
    public DepartmentDTO findById(Long id) {
        return departmentToDTO.apply(departmentRepository.findById(id));
    }

    @Override
    public Collection<DepartmentDTO> findAll() {
        return FluentIterable.from(departmentRepository.findAll()).transform(departmentToDTO).toList();
    }

    @Override
    public void delete(Long id) {
        DepartmentEntity department = departmentRepository.findById(id);
        departmentRepository.delete(department);
    }
}

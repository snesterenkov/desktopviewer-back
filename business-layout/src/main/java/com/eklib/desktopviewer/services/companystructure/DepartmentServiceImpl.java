package com.eklib.desktopviewer.services.companystructure;

import com.eklib.desktopviewer.convertor.fromdto.companystructure.DepartmentFromDTO;
import com.eklib.desktopviewer.convertor.todto.companystructure.DepartmentToDTO;
import com.eklib.desktopviewer.convertor.todto.companystructure.DepartmentToDetailDTO;
import com.eklib.desktopviewer.dto.companystructure.CompanyDTO;
import com.eklib.desktopviewer.dto.companystructure.DepartmentDTO;
import com.eklib.desktopviewer.dto.companystructure.DepartmentDetailDTO;
import com.eklib.desktopviewer.dto.enums.StatusDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.CompanyEntity;
import com.eklib.desktopviewer.persistance.model.companystructure.DepartmentEntity;
import com.eklib.desktopviewer.persistance.model.enums.StatusEnum;
import com.eklib.desktopviewer.persistance.model.security.RoleEntity;
import com.eklib.desktopviewer.persistance.model.security.UserEntity;
import com.eklib.desktopviewer.persistance.repository.companystructure.CompanyRepository;
import com.eklib.desktopviewer.persistance.repository.companystructure.DepartmentRepository;
import com.eklib.desktopviewer.persistance.repository.security.UserRepository;
import com.google.common.collect.FluentIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Set;

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
    private DepartmentToDetailDTO departmentToDetailDTO;

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public DepartmentDTO insert(DepartmentDTO departmentDTO, String client) {
        Assert.hasLength(departmentDTO.getName(), "Department name must not be null and not the empty String.");
        Assert.isNull(departmentDTO.getId(), "Department id is not null");
        boolean hasCompany = companyRepository.hasOpenComponyForClient(departmentDTO.getCompanyid(), client);
        Assert.isTrue(hasCompany, "Company id not exist for client");
        DepartmentEntity newDepartment = departmentFromDTO.apply(departmentDTO);
        UserEntity userEntity = userRepository.getUserByName(client);
        if(userEntity == null){
            Assert.isTrue(false, "Client is null");
            return new DepartmentDTO();
        }
        if(newDepartment != null) {
            newDepartment.setOwner(userEntity);
            DepartmentEntity updatedDepartment = departmentRepository.insert(newDepartment);
            Set<RoleEntity> roles = userEntity.readRoles();
            if(!roles.contains(RoleEntity.DESK_USER_DEPARTMENT)){
                roles.add(RoleEntity.DESK_USER_DEPARTMENT);
                userEntity.writeRoles(roles);
                userRepository.update(userEntity);
            }
            return departmentToDTO.apply(updatedDepartment);
        }
        Assert.isTrue(false, "Cann`t create department");
        return new DepartmentDTO();
    }

    @Override
    public DepartmentDTO update(Long id, DepartmentDTO dto, String client) {
        Assert.hasLength(dto.getName(), "Department name must not be null and not the empty String.");
        boolean hasCompany = companyRepository.hasOpenComponyForClient(dto.getCompanyid(), client);
        Assert.isTrue(hasCompany, "Company id not exist for client");
        dto.setId(id);
        DepartmentEntity newDepartment = departmentFromDTO.apply(dto);
        if(newDepartment != null && newDepartment.getOwner() != null ){
            if(newDepartment.getOwner().getLogin().equals(client)){
                DepartmentEntity updatedDepartment = departmentRepository.update(newDepartment);
                return departmentToDTO.apply(updatedDepartment);
            }
            Assert.isTrue(false, "Cann`t update not your department");
        }
        Assert.isTrue(false, "Cann`t find department");
        return new DepartmentDTO();
    }

    @Override
    public DepartmentDetailDTO findById(Long id, String client) {
        DepartmentEntity departmentEntity = departmentRepository.findById(id);
        if(departmentEntity != null && departmentEntity.getOwner() != null) {
            if (departmentEntity.getOwner().getLogin().equals(client)) {
                return departmentToDetailDTO.apply(departmentEntity);
            }
        }
        Assert.isTrue(false, "Cann`t find company");
        return new DepartmentDetailDTO();
    }

    @Override
    public Collection<DepartmentDetailDTO> findAll(String client) {
        return FluentIterable.from(departmentRepository.findByUser(client)).transform(departmentToDetailDTO).toList();
    }

    @Override
    public DepartmentDetailDTO changeStatus(Long id, StatusDTO statusDTO, String client) {
        DepartmentEntity department = departmentRepository.findById(id);
        StatusEnum newStatus = StatusEnum.valueOf(statusDTO.name());
        StatusEnum comStatusEnum = department.getCompany().getStatus();
        if(comStatusEnum.equals(StatusEnum.OPEN)){
            return changeStatus(department, newStatus,client);
        }
        if(comStatusEnum.equals(StatusEnum.PAUSED) && newStatus.equals(StatusEnum.CLOSED)){
            return changeStatus(department, newStatus,client);
        }
        Assert.isTrue(false, "Cann`t change status in department becouse Company status is " + comStatusEnum);
        return new DepartmentDetailDTO();
    }

    private DepartmentDetailDTO changeStatus(DepartmentEntity department,  StatusEnum newStatus, String client){
        if(department.getOwner().getLogin().equals(client)) {
            if (departmentRepository.changeStatus(department, newStatus)) {
                department = departmentRepository.findById(department.getId());
            }
            return departmentToDetailDTO.apply(department);
        }
        Assert.isTrue(false, "Cann`t change status in company if you not owner");
        return new DepartmentDetailDTO();
    }
}

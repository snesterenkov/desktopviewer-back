package com.eklib.desktopviewer.services.companystructure;

import com.eklib.desktopviewer.dto.companystructure.CompanyDetailDTO;
import com.eklib.desktopviewer.dto.companystructure.DepartmentDTO;
import com.eklib.desktopviewer.dto.companystructure.DepartmentDetailDTO;
import com.eklib.desktopviewer.dto.enums.StatusDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.DepartmentEntity;
import com.eklib.desktopviewer.services.BaseCrudService;

import java.util.Collection;

/**
 * Created by Maxim on 10.11.2014.
 */
public interface DepartmentService {

    DepartmentDTO insert(DepartmentDTO departmentDTO, String client);

    DepartmentDTO update(Long id, DepartmentDTO departmentDTO, String client);

    DepartmentDetailDTO findById(Long id, String client);

    Collection<DepartmentDetailDTO> findAll(String client);

    DepartmentDetailDTO changeStatus(Long id, StatusDTO newStatus, String client);
}

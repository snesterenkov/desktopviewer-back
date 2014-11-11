package com.eklib.desktopviewer.services.department;

import com.eklib.desktopviewer.dto.DepartmentDTO;
import com.eklib.desktopviewer.persistance.model.Department;
import com.eklib.desktopviewer.persistance.repository.DepartmentRepository;
import com.eklib.desktopviewer.services.BaseCrudService;

/**
 * Created by Maxim on 10.11.2014.
 */
public interface DepartmentService extends BaseCrudService<DepartmentDTO, Department, Long, DepartmentRepository> {

}

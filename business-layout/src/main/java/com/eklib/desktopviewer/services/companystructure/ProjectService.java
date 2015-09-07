package com.eklib.desktopviewer.services.companystructure;

import com.eklib.desktopviewer.dto.companystructure.ProjectDTO;
import com.eklib.desktopviewer.dto.companystructure.ProjectDetailDTO;
import com.eklib.desktopviewer.dto.enums.StatusDTO;

import java.util.Collection;

/**
 * Created by vadim on 01.12.2014.
 */
public interface ProjectService {

    ProjectDTO insert(ProjectDTO departmentDTO, String client);

    ProjectDTO update(Long id, ProjectDTO departmentDTO, String client);

    ProjectDetailDTO detailUpdate(Long id, ProjectDetailDTO projectDetailDTO, String client);

    ProjectDetailDTO findById(Long id, String client);

    Collection<ProjectDetailDTO> findAll(String client);

    Collection<ProjectDetailDTO> findForMember(Long userId, String client);

    ProjectDetailDTO changeStatus(Long id, StatusDTO newStatus, String client);
}

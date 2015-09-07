package com.eklib.desktopviewer.persistance.repository.companystructure;

import com.eklib.desktopviewer.persistance.model.companystructure.ProjectEntity;
import com.eklib.desktopviewer.persistance.model.enums.StatusEnum;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepository;

import java.util.List;

/**
 * Created by vadim on 01.12.2014.
 */
public interface ProjectRepository extends BasePagingAndSortingRepository<ProjectEntity, Long> {

    boolean changeStatus(ProjectEntity project, StatusEnum newStatus);

    List findByUser(String client);

    List<ProjectEntity> findForMember(Long userId, String client);
}

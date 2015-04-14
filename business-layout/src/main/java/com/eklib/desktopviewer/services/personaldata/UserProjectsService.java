package com.eklib.desktopviewer.services.personaldata;

import com.eklib.desktopviewer.dto.companystructure.CompaniesProjectsDepartmentsDTO;

/**
 * Created by human on 13.04.2015.
 */
public interface UserProjectsService {

    public CompaniesProjectsDepartmentsDTO getUserProjects(String client);

}

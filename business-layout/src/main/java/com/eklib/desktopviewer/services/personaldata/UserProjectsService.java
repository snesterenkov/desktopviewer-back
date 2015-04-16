package com.eklib.desktopviewer.services.personaldata;

import com.eklib.desktopviewer.dto.companystructure.CompaniesProjectsDepartmentsDTO;
import com.eklib.desktopviewer.dto.companystructure.CompaniesProjectsDepartmentsExtendDTO;

/**
 * Created by human on 13.04.2015.
 */
public interface UserProjectsService {

    public CompaniesProjectsDepartmentsExtendDTO getUserProjects(String client);

}

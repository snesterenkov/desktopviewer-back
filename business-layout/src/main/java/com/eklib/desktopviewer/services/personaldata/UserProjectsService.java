package com.eklib.desktopviewer.services.personaldata;

import com.eklib.desktopviewer.dto.companystructure.ProjectDTO;
import com.eklib.desktopviewer.dto.personaldata.UserProjectDTO;

import java.util.List;

/**
 * Created by human on 07.04.2015.
 */
public interface UserProjectsService {

    public List<UserProjectDTO> getUserProjects(String client);

}

package com.eklib.desktopviewer.api.v1.personaldata;

import com.eklib.desktopviewer.dto.companystructure.CompanyDTO;
import com.eklib.desktopviewer.dto.companystructure.CompanyDetailDTO;
import com.eklib.desktopviewer.dto.companystructure.ProjectDTO;
import com.eklib.desktopviewer.dto.enums.StatusDTO;
import com.eklib.desktopviewer.dto.personaldata.UserProjectDTO;
import com.eklib.desktopviewer.services.personaldata.UserProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by human on 07.04.2015.
 */
@RestController
@RequestMapping("/user_projects")
public class UserProjectsController {

    @Autowired
    private UserProjectsService userProjectsService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserProjectDTO> getUserProjects(@RequestParam(value = "client", required = false) String client){
        return userProjectsService.getUserProjects(client);
    }

}

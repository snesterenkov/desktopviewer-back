package com.eklib.desktopviewer.api.v1.personaldata;

import com.eklib.desktopviewer.dto.companystructure.CompaniesProjectsDepartmentsDTO;
import com.eklib.desktopviewer.dto.companystructure.CompaniesProjectsDepartmentsExtendDTO;
import com.eklib.desktopviewer.services.companystructure.CompanyServices;
import com.eklib.desktopviewer.services.personaldata.UserProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by human on 13.04.2015.
 */
@RestController
@RequestMapping("/personal")
public class PersonalDataController {

    @Autowired
    private UserProjectsService userProjectsService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/projects", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CompaniesProjectsDepartmentsExtendDTO getUserProjects(@RequestParam(value = "client", required = true)String client){
        return userProjectsService.getUserProjects(client);
    }


}

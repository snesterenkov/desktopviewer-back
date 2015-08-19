package com.eklib.desktopviewer.api.v1.companystructure;

import com.eklib.desktopviewer.dto.companystructure.ProjectDTO;
import com.eklib.desktopviewer.dto.companystructure.ProjectDetailDTO;
import com.eklib.desktopviewer.dto.enums.StatusDTO;
import com.eklib.desktopviewer.services.companystructure.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadim on 01.12.2014.
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ProjectDTO createProject(@RequestBody ProjectDTO projectDTO, @RequestParam(value = "client", required = false) String client){
        return projectService.insert(projectDTO, client);
    }

    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<ProjectDetailDTO> findAllProjects(@RequestParam(value = "client", required = false) String client){
        return new ArrayList<ProjectDetailDTO>(projectService.findAll(client));
    }

    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ProjectDetailDTO findById(@PathVariable("id") Long projectId, @RequestParam(value = "client", required = false) String client){
        return projectService.findById(projectId,client);
    }

    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ProjectDTO updateProject(@PathVariable("id") Long projectId, @RequestBody ProjectDTO projectDTO, @RequestParam(value = "client", required = false) String client){
        return projectService.update(projectId, projectDTO, client);
    }

    @RequestMapping(value = "/detailupdate/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ProjectDetailDTO detailUpdateProject(@PathVariable("id") Long projectId,
                                                @RequestParam(value = "client", required = false) String client,
                                                @RequestBody ProjectDetailDTO projectDetailDTO){
        return projectService.detailUpdate(projectId, projectDetailDTO, client);
    }


    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/changestatus/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ProjectDetailDTO changeStatus(@PathVariable("id") Long id, @RequestParam(value = "client", required = false) String client, @RequestBody StatusDTO newStatus){
        return projectService.changeStatus(id,newStatus, client);
    }
}

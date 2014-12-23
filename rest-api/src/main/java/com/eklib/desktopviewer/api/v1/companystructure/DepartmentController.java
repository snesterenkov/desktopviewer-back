package com.eklib.desktopviewer.api.v1.companystructure;

import com.eklib.desktopviewer.dto.companystructure.DepartmentDTO;
import com.eklib.desktopviewer.dto.companystructure.DepartmentDetailDTO;
import com.eklib.desktopviewer.dto.enums.StatusDTO;
import com.eklib.desktopviewer.services.companystructure.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxim on 10.11.2014.
 */

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public DepartmentDTO createDepartment(@RequestBody DepartmentDTO departmentDTO, @RequestParam(value = "client", required = false) String client){
        return departmentService.insert(departmentDTO, client);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<DepartmentDetailDTO> findAllDepartments(@RequestParam(value = "client", required = false) String client){
        return new ArrayList<DepartmentDetailDTO>(departmentService.findAll(client));
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DepartmentDetailDTO findById(@PathVariable("id") Long departmentId, @RequestParam(value = "client", required = false) String client){
        return departmentService.findById(departmentId,client);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DepartmentDTO updateDepartment(@PathVariable("id") Long departmentId, @RequestBody DepartmentDTO departmentDTO,  @RequestParam(value = "client", required = false) String client){
        return departmentService.update(departmentId, departmentDTO, client);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/changestatus/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DepartmentDetailDTO changeStatus(@PathVariable("id") Long id, @RequestParam(value = "client", required = false) String client, @RequestBody StatusDTO newStatus){
        return departmentService.changeStatus(id,newStatus, client);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/open", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<DepartmentDetailDTO> findOpenCompanies(@RequestParam(value = "client", required = false) String client){
        return new ArrayList<DepartmentDetailDTO>(departmentService.findOpen(client));
    }
}

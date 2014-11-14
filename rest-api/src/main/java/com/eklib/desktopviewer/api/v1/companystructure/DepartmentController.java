package com.eklib.desktopviewer.api.v1.companystructure;

import com.eklib.desktopviewer.dto.companystructure.DepartmentDTO;
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
    DepartmentService departmentService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public DepartmentDTO createDepartment(@RequestBody DepartmentDTO departmentDTO){
        return departmentService.insert(departmentDTO);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<DepartmentDTO> findAllDepartments(){
        return new ArrayList<DepartmentDTO>(departmentService.findAll());
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DepartmentDTO findById(@PathVariable("id") Long departmentId){
        return departmentService.findById(departmentId);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DepartmentDTO updateDepartment(@PathVariable("id") Long departmentId, @RequestBody DepartmentDTO departmentDTO){
        return departmentService.update(departmentId, departmentDTO);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void deleteDepartment(@PathVariable("id") Long departmentId){
        departmentService.delete(departmentId);
    }
}

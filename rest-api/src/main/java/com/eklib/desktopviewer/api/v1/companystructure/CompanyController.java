package com.eklib.desktopviewer.api.v1.companystructure;

import com.eklib.desktopviewer.dto.companystructure.CompanyDTO;
import com.eklib.desktopviewer.services.companystructure.CompanyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyServices companyServices;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CompanyDTO createCompany(@RequestBody CompanyDTO company){
        return companyServices.insert(company);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CompanyDTO updateCompany(@PathVariable("id")Long id, @RequestBody CompanyDTO companyDTO){
        return companyServices.update(id, companyDTO);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<CompanyDTO> findAllCompanies(){
        return new ArrayList<CompanyDTO>(companyServices.findAll());
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void delete(@PathVariable("id") Long id){
        companyServices.delete(id);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CompanyDTO findCompanyById(@PathVariable("id") Long id){
        return companyServices.findById(id);
    }
}

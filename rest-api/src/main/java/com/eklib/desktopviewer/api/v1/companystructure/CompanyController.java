package com.eklib.desktopviewer.api.v1.companystructure;

import com.eklib.desktopviewer.dto.companystructure.CompanyDTO;
import com.eklib.desktopviewer.dto.companystructure.CompanyDetailDTO;
import com.eklib.desktopviewer.dto.enums.StatusDTO;
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
    private CompanyServices companyServices;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CompanyDTO createCompany(@RequestBody CompanyDTO company, @RequestParam(value = "client", required = false) String client){
        return companyServices.insert(company, client);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CompanyDTO updateCompany(@PathVariable("id")Long id, @RequestBody CompanyDTO companyDTO, @RequestParam(value = "client", required = false) String client){
        return companyServices.update(id, companyDTO,client);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<CompanyDetailDTO> findAllCompanies(@RequestParam(value = "client", required = false) String client){
        return new ArrayList<CompanyDetailDTO>(companyServices.findAll(client));
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/changestatus/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CompanyDetailDTO changeStatus(@PathVariable("id") Long id, @RequestParam(value = "client", required = false) String client, @RequestBody StatusDTO newStatus){
        return companyServices.changeStatus(id,newStatus, client);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CompanyDetailDTO findCompanyById(@PathVariable("id") Long id, @RequestParam(value = "client", required = false) String client){
        return companyServices.findById(id,client);

    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/open", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<CompanyDetailDTO> findOpenCompanies(@RequestParam(value = "client", required = false) String client){
        return new ArrayList<CompanyDetailDTO>(companyServices.findOpen(client));
    }
}

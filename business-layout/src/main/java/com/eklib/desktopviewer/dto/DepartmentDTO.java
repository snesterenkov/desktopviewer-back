package com.eklib.desktopviewer.dto;

import com.eklib.desktopviewer.persistance.model.Company;

/**
 * Created by Maxim on 10.11.2014.
 */
public class DepartmentDTO extends BaseDTO{

    String name;
    CompanyDTO companyDTO;

    public CompanyDTO getCompanyDTO() {
        return companyDTO;
    }

    public void setCompanyDTO(CompanyDTO companyDTO) {
        this.companyDTO = companyDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.eklib.desktopviewer.dto.companystructure;

import com.eklib.desktopviewer.dto.BaseDTO;
import com.eklib.desktopviewer.dto.companystructure.CompanyDTO;

/**
 * Created by Maxim on 10.11.2014.
 */
public class DepartmentDTO extends BaseDTO {

    String name;
    Long companyid;

    public Long getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Long companyid) {
        this.companyid = companyid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
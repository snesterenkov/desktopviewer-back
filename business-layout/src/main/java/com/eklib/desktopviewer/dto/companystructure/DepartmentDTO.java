package com.eklib.desktopviewer.dto.companystructure;

import com.eklib.desktopviewer.dto.BaseDTO;

/**
 * Created by Maxim on 10.11.2014.
 */
public class DepartmentDTO extends BaseDTO {

    private String name;
    private Long companyId;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

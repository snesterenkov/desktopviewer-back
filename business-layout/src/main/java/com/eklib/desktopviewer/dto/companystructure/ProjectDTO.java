package com.eklib.desktopviewer.dto.companystructure;

import com.eklib.desktopviewer.dto.BaseDTO;

/**
 * Created by vadim on 01.12.2014.
 */
public class ProjectDTO extends BaseDTO{

    private String name;
    private Long departmentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}

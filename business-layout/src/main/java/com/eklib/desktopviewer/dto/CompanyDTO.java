package com.eklib.desktopviewer.dto;

import java.util.List;

public class CompanyDTO extends BaseDTO{
    String name;
    List<DepartmentDTO> departmentsDTO;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DepartmentDTO> getDepartmentsDTO() {
        return departmentsDTO;
    }

    public void setDepartmentsDTO(List<DepartmentDTO> departmentsDTO) {
        this.departmentsDTO = departmentsDTO;
    }
}

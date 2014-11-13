package com.eklib.desktopviewer.dto.companystructure;

import java.util.List;

/**
 * Created by vadim on 13.11.2014.
 */
public class CompanyDetailDTO extends CompanyDTO {

    List<DepartmentDTO> departmentsDTO;

    public List<DepartmentDTO> getDepartmentsDTO() {
        return departmentsDTO;
    }

    public void setDepartmentsDTO(List<DepartmentDTO> departmentsDTO) {
        this.departmentsDTO = departmentsDTO;
    }
}

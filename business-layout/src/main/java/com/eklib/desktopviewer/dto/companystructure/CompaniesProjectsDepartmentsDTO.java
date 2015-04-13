package com.eklib.desktopviewer.dto.companystructure;

import java.util.List;

/**
 * Created by alex on 4/10/2015.
 */
public class CompaniesProjectsDepartmentsDTO {

    private List<CompanyDTO> companiesDTO;
    private List<DepartmentDTO> departmentDTOs;
    private List<ProjectDTO> projectDTOs;

    public List<DepartmentDTO> getDepartmentDTOs() {
        return departmentDTOs;
    }

    public void setDepartmentDTOs(List<DepartmentDTO> departmentDTOs) {
        this.departmentDTOs = departmentDTOs;
    }

    public List<ProjectDTO> getProjectDTOs() {
        return projectDTOs;
    }

    public void setProjectDTOs(List<ProjectDTO> projectDTOs) {
        this.projectDTOs = projectDTOs;
    }


    public List<CompanyDTO> getCompaniesDTO() {
        return companiesDTO;
    }

    public void setCompaniesDTO(List<CompanyDTO> companiesDTO) {
        this.companiesDTO = companiesDTO;
    }
}

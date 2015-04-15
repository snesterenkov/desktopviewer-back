package com.eklib.desktopviewer.dto.companystructure;

import java.util.List;

/**
 * Created by alex on 4/10/2015.
 */
public class CompaniesProjectsDepartmentsDTO {

    private List<CompanyDetailDTO> companiesDetailsDTO;
    private List<DepartmentDetailDTO> departmentDetailsDTOs;
    private List<ProjectDetailDTO> projectDetailDTOs;

    public List<DepartmentDetailDTO> getDepartmentDetailsDTOs() {
        return departmentDetailsDTOs;
    }

    public void setDepartmentDetailsDTOs(List<DepartmentDetailDTO> departmentDetailsDTOs) {
        this.departmentDetailsDTOs = departmentDetailsDTOs;
    }

    public List<ProjectDetailDTO> getProjectDetailDTOs() {
        return projectDetailDTOs;
    }

    public void setProjectDetailDTOs(List<ProjectDetailDTO> projectDetailDTOs) {
        this.projectDetailDTOs = projectDetailDTOs;
    }


    public List<CompanyDetailDTO> getCompaniesDetailsDTO() {
        return companiesDetailsDTO;
    }

    public void setCompaniesDetailsDTO(List<CompanyDetailDTO> companiesDetailsDTO) {
        this.companiesDetailsDTO = companiesDetailsDTO;
    }
}

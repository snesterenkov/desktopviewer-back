package com.eklib.desktopviewer.dto.companystructure;

import com.eklib.desktopviewer.dto.enums.StatusDTO;

import java.util.List;

/**
 * Created by vadim on 01.12.2014.
 */
public class DepartmentDetailDTO extends  DepartmentDTO {

    private StatusDTO status;

    private StatusDTO parentStatus;

    private CompanyDTO companyDTO;

    private List<ProjectDTO> projectDTOs;

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }

    public CompanyDTO getCompanyDTO() {
        return companyDTO;
    }

    public void setCompanyDTO(CompanyDTO companyDTO) {
        this.companyDTO = companyDTO;
    }

    public StatusDTO getParentStatus() {
        return parentStatus;
    }

    public void setParentStatus(StatusDTO parentStatus) {
        this.parentStatus = parentStatus;
    }

    public List<ProjectDTO> getProjectDTOs() {
        return projectDTOs;
    }

    public void setProjectDTOs(List<ProjectDTO> projectDTOs) {
        this.projectDTOs = projectDTOs;
    }
}

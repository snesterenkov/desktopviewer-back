package com.eklib.desktopviewer.dto.companystructure;

import com.eklib.desktopviewer.dto.enums.StatusDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadim on 13.11.2014.
 */
public class CompanyDetailDTO extends CompanyDTO {

    private StatusDTO status;

    private List<DepartmentDTO> departmentsDTO = new ArrayList<DepartmentDTO>();

    public List<DepartmentDTO> getDepartmentsDTO() {
        return departmentsDTO;
    }

    public void setDepartmentsDTO(List<DepartmentDTO> departmentsDTO) {
        this.departmentsDTO = departmentsDTO;
    }

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }
}

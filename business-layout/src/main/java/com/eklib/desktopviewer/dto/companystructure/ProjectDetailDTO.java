package com.eklib.desktopviewer.dto.companystructure;

import com.eklib.desktopviewer.dto.enums.StatusDTO;

/**
 * Created by vadim on 01.12.2014.
 */
public class ProjectDetailDTO extends ProjectExtendDTO {

    private StatusDTO parentStatus;

    private DepartmentDTO departmentDTO;

    public DepartmentDTO getDepartmentDTO() {
        return departmentDTO;
    }

    public void setDepartmentDTO(DepartmentDTO departmentDTO) {
        this.departmentDTO = departmentDTO;
    }

    public StatusDTO getParentStatus() {
        return parentStatus;
    }

    public void setParentStatus(StatusDTO parentStatus) {
        this.parentStatus = parentStatus;
    }
}

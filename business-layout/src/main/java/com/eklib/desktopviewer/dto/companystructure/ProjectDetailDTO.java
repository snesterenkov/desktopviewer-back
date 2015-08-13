package com.eklib.desktopviewer.dto.companystructure;

import com.eklib.desktopviewer.dto.enums.StatusDTO;
import com.eklib.desktopviewer.dto.security.UserDTO;

import java.util.List;

/**
 * Created by vadim on 01.12.2014.
 */
public class ProjectDetailDTO extends ProjectDTO {

    private StatusDTO status;

    private StatusDTO parentStatus;

    private DepartmentDTO departmentDTO;

    public List<UserDTO> getUserDTOs() {
        return userDTOs;
    }

    public void setUserDTOs(List<UserDTO> userDTOs) {
        this.userDTOs = userDTOs;
    }

    private List<UserDTO> userDTOs;

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }

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

package com.eklib.desktopviewer.dto.companystructure;

import com.eklib.desktopviewer.dto.enums.StatusDTO;

/**
 * Created by human on 10.04.2015.
 */
public class DepartmentExtendDTO extends DepartmentDTO {

    private StatusDTO status;

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }
}

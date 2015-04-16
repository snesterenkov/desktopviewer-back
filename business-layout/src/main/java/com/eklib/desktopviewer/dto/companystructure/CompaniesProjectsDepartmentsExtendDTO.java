package com.eklib.desktopviewer.dto.companystructure;

import java.util.List;

/**
 * Created by human on 16.04.2015.
 */
public class CompaniesProjectsDepartmentsExtendDTO extends CompaniesProjectsDepartmentsDTO {

    private List<Boolean> isProjectOwner;

    public List<Boolean> getIsProjectOwner() {
        return isProjectOwner;
    }

    public void setIsProjectOwner(List<Boolean> isProjectOwner) {
        this.isProjectOwner = isProjectOwner;
    }
}

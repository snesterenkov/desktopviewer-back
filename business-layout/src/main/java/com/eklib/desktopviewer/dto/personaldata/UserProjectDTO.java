package com.eklib.desktopviewer.dto.personaldata;

import com.eklib.desktopviewer.dto.companystructure.CompanyDTO;
import com.eklib.desktopviewer.dto.companystructure.DepartmentDTO;
import com.eklib.desktopviewer.dto.companystructure.ProjectDTO;

/**
 * Created by human on 07.04.2015.
 */
public class UserProjectDTO {

    private ProjectDTO projectDTO;

    private DepartmentDTO departmentDTO;

    private CompanyDTO companyDTO;

    private boolean isOwner;

    public UserProjectDTO(){}

    public ProjectDTO getProjectDTO(){
        return projectDTO;
    }

    public void setProjectDTO(ProjectDTO projectDTO){
        this.projectDTO = projectDTO;
    }

    public DepartmentDTO getDepartmentDTO(){
        return departmentDTO;
    }

    public void setDepartmentDTO(DepartmentDTO departmentDTO){
        this.departmentDTO = departmentDTO;
    }

    public CompanyDTO getCompanyDTO(){
        return companyDTO;
    }

    public void setCompanyDTO(CompanyDTO companyDTO){
        this.companyDTO = companyDTO;
    }

    public boolean getIsOwner(){
        return isOwner;
    }

    public void setIsOwner(boolean isowner){
        this.isOwner = isowner;
    }
}

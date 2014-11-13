package com.eklib.desktopviewer.persistance.model.companystructure;

import com.eklib.desktopviewer.persistance.model.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CLIENT_COMPANY")
public class CompanyEntity extends BaseEntity implements Serializable{

    @Column(name = "NAME")
    String name;

    @Column
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    List<DepartmentEntity> departments = new ArrayList<>(0);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DepartmentEntity> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentEntity> departments) {
        this.departments = departments;
    }
}

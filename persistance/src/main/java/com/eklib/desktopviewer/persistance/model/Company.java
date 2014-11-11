package com.eklib.desktopviewer.persistance.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CLIENT_COMPANY")
public class Company extends BaseEntity implements Serializable{

    @Column(name = "NAME")
    String name;

    @Column
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    List<Department> departments = new ArrayList<>(0);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}

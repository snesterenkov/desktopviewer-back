package com.eklib.desktopviewer.persistance.model.companystructure;

import com.eklib.desktopviewer.persistance.model.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Maxim on 10.11.2014.
 */

@Entity
public class DepartmentEntity extends BaseEntity implements Serializable{

    @Column(name = "NAME")
    String name;
    @JoinColumn(name = "ID_CLIENT_COMPANY", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    CompanyEntity company;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }
}

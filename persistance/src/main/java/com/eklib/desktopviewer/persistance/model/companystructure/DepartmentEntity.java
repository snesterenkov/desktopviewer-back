package com.eklib.desktopviewer.persistance.model.companystructure;

import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.model.enums.StatusEnum;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Maxim on 10.11.2014.
 */

@Entity
@Table(name = "DEPARTMENT")
public class DepartmentEntity extends BaseEntity implements Serializable{

    @Column(name = "NAME")
    String name;
    @JoinColumn(name = "ID_CLIENT_COMPANY", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    CompanyEntity company;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private StatusEnum status = StatusEnum.OPEN;

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

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}

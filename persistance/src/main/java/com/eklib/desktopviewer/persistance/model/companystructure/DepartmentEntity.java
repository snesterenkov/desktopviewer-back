package com.eklib.desktopviewer.persistance.model.companystructure;

import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.model.enums.StatusEnum;
import com.eklib.desktopviewer.persistance.model.security.UserEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxim on 10.11.2014.
 */

@Entity
@Table(name = "DEPARTMENT")
public class DepartmentEntity extends BaseEntity implements Serializable{

    @Column(name = "NAME")
    private String name;
    @JoinColumn(name = "ID_CLIENT_COMPANY", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private CompanyEntity company;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private StatusEnum status = StatusEnum.OPEN;

    @JoinColumn(name = "ID_OWNER", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity owner;

    @Column
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<ProjectEntity> projects = new ArrayList<>(0);

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

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public List<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectEntity> projects) {
        this.projects = projects;
    }

}

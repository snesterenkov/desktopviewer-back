package com.eklib.desktopviewer.persistance.model.companystructure;

import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.model.enums.StatusEnum;
import com.eklib.desktopviewer.persistance.model.security.UserEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "CLIENT_COMPANY")
public class CompanyEntity extends BaseEntity implements Serializable{

    @Column(name = "NAME")
    private String name;

    @Column
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<DepartmentEntity> departments;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private StatusEnum status = StatusEnum.OPEN;

    @JoinColumn(name = "ID_OWNER", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DepartmentEntity> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<DepartmentEntity> departments) {
        this.departments = departments;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}

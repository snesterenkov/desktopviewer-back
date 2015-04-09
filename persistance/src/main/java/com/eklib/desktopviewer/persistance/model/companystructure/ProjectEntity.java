package com.eklib.desktopviewer.persistance.model.companystructure;

import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.model.enums.StatusEnum;
import com.eklib.desktopviewer.persistance.model.security.UserEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by vadim on 01.12.2014.
 */
@Entity
@Table(name = "PROJECT")
public class ProjectEntity extends BaseEntity {

    @Column(name = "NAME")
    private String name;

    @JoinColumn(name = "ID_DEPARTMENT", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private DepartmentEntity department;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private StatusEnum status = StatusEnum.OPEN;

    @JoinColumn(name = "ID_OWNER", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity owner;

    @ManyToMany(targetEntity = ProjectEntity.class, fetch = FetchType.LAZY)
    private List<UserEntity> userEntities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public List<UserEntity> getUserEntities(){
        return userEntities;
    }

    public void setUserEntities(List<UserEntity> userEntities){
        this.userEntities = userEntities;
    }

}

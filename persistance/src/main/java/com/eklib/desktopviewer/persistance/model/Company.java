package com.eklib.desktopviewer.persistance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "CLIENT_COMPANY")
public class Company extends BaseEntity implements Serializable{

    @Column(name = "NAME")
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

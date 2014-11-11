package com.eklib.desktopviewer.persistance.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Maxim on 10.11.2014.
 */

@Entity
public class Department extends BaseEntity implements Serializable{

    @Column(name = "NAME")
    String name;
    @JoinColumn(name = "ID_CLIENT_COMPANY", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    Company company;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}

package com.eklib.desktopviewer.persistance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Created by Maxim on 13.11.2014.
 */
@Entity
@Table(name = "TEST")
public class TestEntity extends BaseEntity {

    @Column(name = "NOTE")
    String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

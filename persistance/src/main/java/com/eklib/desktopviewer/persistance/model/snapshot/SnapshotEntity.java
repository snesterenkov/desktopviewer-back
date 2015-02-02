package com.eklib.desktopviewer.persistance.model.snapshot;

import com.eklib.desktopviewer.persistance.model.BaseEntity;
import com.eklib.desktopviewer.persistance.model.companystructure.ProjectEntity;
import com.eklib.desktopviewer.persistance.model.security.UserEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by vadim on 17.12.2014.
 */
@Entity
@Table(name = "SNAPSHOT")
public class SnapshotEntity extends BaseEntity {

    @Column(name = "NOTE")
    private String note;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "DATE")
    private Date date;

    @JoinColumn(name = "ID_PROJECT", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private ProjectEntity project;

    @Column(name = "FILENAME")
    private String filename;

    @JoinColumn(name = "ID_USER", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @Column(name = "COUNT_MOUSE_CLICK")
    private Integer countMouseClick;

    @Column(name = "COUNT_KEYBOARD_CLICK")
    private Integer countKeyboardClick;

    @Column(name = "TIME_INTERVAL")
    private Integer timeInterval;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Integer getCountMouseClick() {
        return countMouseClick;
    }

    public void setCountMouseClick(Integer countMouseClick) {
        this.countMouseClick = countMouseClick;
    }

    public Integer getCountKeyboardClick() {
        return countKeyboardClick;
    }

    public void setCountKeyboardClick(Integer countKeyboardClick) {
        this.countKeyboardClick = countKeyboardClick;
    }

    public Integer getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(Integer timeInterval) {
        this.timeInterval = timeInterval;
    }
}

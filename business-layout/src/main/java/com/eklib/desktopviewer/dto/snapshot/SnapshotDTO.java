package com.eklib.desktopviewer.dto.snapshot;

import com.eklib.desktopviewer.dto.BaseDTO;
import com.eklib.desktopviewer.serializer.CustomDateSerializer;
import com.eklib.desktopviewer.serializer.CustomTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;


/**
 * Created by vadim on 18.12.2014.
 */
public class SnapshotDTO extends BaseDTO {

    private static final int percent = 100;

    private byte[] file;

    private String note;

    private String message;

    private String fileName;

    private Long progectId;

    private Integer countMouseClick;

    private Integer countKeyboardClick;

    private Integer timeInterval;

    private Integer userActivityPercent;

    private Date date;

    private Date time;

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

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

    public Long getProgectId() {
        return progectId;
    }

    public void setProgectId(Long progectId) {
        this.progectId = progectId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public Integer getUserActivityPercent() {
        int totalUserClick = getCountKeyboardClick() + getCountMouseClick();
        return (totalUserClick * percent) / timeInterval;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @JsonSerialize(using = CustomTimeSerializer.class)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

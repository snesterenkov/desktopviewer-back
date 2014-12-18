package com.eklib.desktopviewer.dto.snapshot;

import com.eklib.desktopviewer.dto.BaseDTO;

/**
 * Created by vadim on 18.12.2014.
 */
public class SnapshotDTO extends BaseDTO {

    private byte[] file;

    private String note;

    private String message;

    private Long progectId;

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
}

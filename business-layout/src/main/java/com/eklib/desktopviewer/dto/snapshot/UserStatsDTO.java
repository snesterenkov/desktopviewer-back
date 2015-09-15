package com.eklib.desktopviewer.dto.snapshot;

import com.eklib.desktopviewer.serializer.CustomTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * Created by s.sheman on 15.09.2015.
 */
public class UserStatsDTO {

    private Long userId;

    private Date startTime;

    private Long snapshotsCount;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @JsonSerialize(using = CustomTimeSerializer.class)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Long getSnapshotsCount() {
        return snapshotsCount;
    }

    public void setSnapshotsCount(Long snapshotsCount) {
        this.snapshotsCount = snapshotsCount;
    }
}

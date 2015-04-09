package com.eklib.desktopviewer.dto.report;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by alex on 4/6/2015.
 */
@Component
public class PeriodDTO implements Serializable{

    private Date startDate;

    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}

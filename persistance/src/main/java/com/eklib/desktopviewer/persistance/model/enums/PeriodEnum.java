package com.eklib.desktopviewer.persistance.model.enums;

/**
 * Created by alex on 4/3/2015.
 */
public enum PeriodEnum {

    DAY(1),
    WEEK(7),
    MONTH(30),
    YEAR(365);

    private Integer durationDays;

    PeriodEnum(Integer durationDays) {
       this.durationDays = durationDays;
    }


    public Integer getDurationDays() {
        return durationDays;
    }
}

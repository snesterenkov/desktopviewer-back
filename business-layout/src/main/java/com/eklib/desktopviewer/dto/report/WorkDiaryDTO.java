package com.eklib.desktopviewer.dto.report;

import com.eklib.desktopviewer.dto.security.UserDTO;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 4/2/2015.
 */
@Component
public class WorkDiaryDTO implements Serializable {

    private UserDTO user;
    private Map<Date,Double> hoursDate;
    private List<PeriodDTO> periodDTOs;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Map<Date, Double> getHoursDate() {
        return hoursDate;
    }

    public void setHoursDate(Map<Date, Double> hoursDate) {
        this.hoursDate = hoursDate;
    }

    public List<PeriodDTO> getPeriodDTOs() {
        return periodDTOs;
    }

    public void setPeriodDTOs(List<PeriodDTO> periodDTOs) {
        this.periodDTOs = periodDTOs;
    }
}

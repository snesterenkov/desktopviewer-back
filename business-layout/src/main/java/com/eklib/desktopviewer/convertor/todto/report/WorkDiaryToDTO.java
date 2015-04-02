package com.eklib.desktopviewer.convertor.todto.report;

import com.eklib.desktopviewer.dto.report.WorkDiaryDTO;
import com.eklib.desktopviewer.dto.security.UserDTO;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * Created by alex on 4/2/2015.
 */
@Component
public class WorkDiaryToDTO  {

    public WorkDiaryDTO apply(Map<Date,Double> hoursOnDate, UserDTO user) {
        WorkDiaryDTO workDiaryDTO = new WorkDiaryDTO();
        workDiaryDTO.setUser(user);
        workDiaryDTO.setHoursDate(hoursOnDate);
        return workDiaryDTO;
    }
}

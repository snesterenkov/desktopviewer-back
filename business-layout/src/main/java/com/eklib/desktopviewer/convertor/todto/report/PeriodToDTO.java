package com.eklib.desktopviewer.convertor.todto.report;

import com.eklib.desktopviewer.dto.report.PeriodDTO;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by alex on 4/6/2015.
 */
@Component
public class PeriodToDTO {

    public PeriodDTO apply(Date startDate, Date endDate) {
        PeriodDTO periodDTO = new PeriodDTO();
        periodDTO.setEndDate(endDate);
        periodDTO.setStartDate(startDate);
        return periodDTO;
    }
}

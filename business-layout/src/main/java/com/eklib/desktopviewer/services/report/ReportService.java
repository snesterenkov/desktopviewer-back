package com.eklib.desktopviewer.services.report;

import com.eklib.desktopviewer.dto.report.WorkDiaryDTO;

import java.util.Date;
import java.util.List;

/**
 * Created by alex on 4/1/2015.
 */
public interface ReportService {

    List<WorkDiaryDTO> getWorkingHoursByTimePeriod(Date startDate, Date endDate);
}

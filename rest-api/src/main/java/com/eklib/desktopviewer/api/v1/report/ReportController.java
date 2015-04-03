package com.eklib.desktopviewer.api.v1.report;

import com.eklib.desktopviewer.dto.report.WorkDiaryDTO;
import com.eklib.desktopviewer.persistance.model.enums.PeriodEnum;
import com.eklib.desktopviewer.services.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by alex on 4/1/2015.
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value= "/workDiary", method = RequestMethod.GET, headers="Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<WorkDiaryDTO> getHoursByUserFromPeriod(
                                      @RequestParam(value = "period", required = true) PeriodEnum period,
                                      @RequestParam(value = "startDate", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                      @RequestParam(value = "endDate", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
                                      @RequestParam(value = "client", required = false) String client){
        return reportService.getWorkingHoursByTimePeriod(period, startDate, endDate);
    }

}

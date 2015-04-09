package com.eklib.desktopviewer.services.report;

import com.eklib.desktopviewer.convertor.todto.report.PeriodToDTO;
import com.eklib.desktopviewer.convertor.todto.report.WorkDiaryToDTO;
import com.eklib.desktopviewer.convertor.todto.security.UserToDTO;
import com.eklib.desktopviewer.dto.report.PeriodDTO;
import com.eklib.desktopviewer.dto.report.WorkDiaryDTO;
import com.eklib.desktopviewer.dto.security.UserDTO;
import com.eklib.desktopviewer.persistance.model.enums.PeriodEnum;
import com.eklib.desktopviewer.persistance.model.security.UserEntity;
import com.eklib.desktopviewer.persistance.repository.security.UserRepository;
import com.eklib.desktopviewer.persistance.repository.snapshot.SnapshotRepository;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by alex on 4/1/2015.
 */
@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    private static final int countMinutesInHour = 60;
    private static final int countMinutesBetweenTwoSnapshots = 10;

    @Autowired
    private SnapshotRepository snapshotRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkDiaryToDTO workDiaryToDTO;
    @Autowired
    private PeriodToDTO periodToDTO;

    @Autowired
    private UserToDTO userToDTO;

    private  List<PeriodDTO> periodDTOs;

    public List<WorkDiaryDTO> getWorkingHoursByTimePeriod(PeriodEnum period, Date startDate, Date endDate) {
        List<WorkDiaryDTO> workDiaryDTOs = new ArrayList<WorkDiaryDTO>();

        List<UserEntity> userEntities = userRepository.findAll();
        Map<Date, Double> countHoursOnDate = new HashMap<Date, Double>();

        for(UserEntity userEntity:userEntities) {
            countHoursOnDate = calculateWorkingHoursOnDate(period, userEntity, startDate, endDate);
            UserDTO user = userToDTO.apply(userEntity);
            WorkDiaryDTO workDiaryDTO = workDiaryToDTO.apply(countHoursOnDate,user,periodDTOs);
            workDiaryDTOs.add(workDiaryDTO);
        }

        return workDiaryDTOs;
    }


    private Map<Date, Double> calculateWorkingHoursOnDate(PeriodEnum period, UserEntity userEntity, Date startDate, Date endDate) {
        int countSnapshots = 0;
        double workingHours = 0;
        PeriodDTO periodDTO = null;
        Map<Date, Double> dateAndCountHours = new HashMap<Date, Double>();
        periodDTOs = new ArrayList<PeriodDTO>();

        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        Calendar temp = Calendar.getInstance();
        int dayOfWeek = 0;
        int dayOfMonth = 0;
        int dayInMonth = 0;
        int dayInWeek = 7;
        int remainDaysToEndPeriod = 0;
        Date date = null;


        while(!start.equals(end)) {

            if(period.DAY.equals(period)) {
                periodDTO = periodToDTO.apply(start.getTime(),start.getTime());
                periodDTOs.add(periodDTO);
                countSnapshots = getCountSnapshots(start.getTime(),1,userEntity);
                workingHours = ((double)(countSnapshots * countMinutesBetweenTwoSnapshots)) / countMinutesInHour;
                start.add(Calendar.DATE, 1);
                dateAndCountHours.put(start.getTime(),workingHours);

            } else if (period.WEEK.equals(period)) {
                dayOfWeek = start.get(Calendar.DAY_OF_WEEK);
                temp = start;

                //start of period
                if(dayOfWeek != dayInWeek && (Days.daysBetween(new DateTime(start), new DateTime(end.getTime())).getDays() >= dayInWeek)) {
                    remainDaysToEndPeriod = dayInWeek - dayOfWeek;
                    date = new Date(temp.getTime().getTime());
                //end of period
                }  else if(Days.daysBetween(new DateTime(start), new DateTime(end.getTime())).getDays() < dayInWeek) {
                    remainDaysToEndPeriod = Days.daysBetween(new DateTime(start), new DateTime(end)).getDays();
                    date = new Date(temp.getTime().getTime() + TimeUnit.DAYS.toMillis(1));
                //middle of period
                }  else {
                    remainDaysToEndPeriod = dayInWeek;
                    date = new Date(temp.getTime().getTime() + TimeUnit.DAYS.toMillis(1));
                }


                countSnapshots = getCountSnapshots(start.getTime(),remainDaysToEndPeriod,userEntity);
                workingHours = ((double)(countSnapshots * countMinutesBetweenTwoSnapshots)) / countMinutesInHour;
                start.add(Calendar.DATE, remainDaysToEndPeriod);

                periodDTO = periodToDTO.apply(date,start.getTime());
                periodDTOs.add(periodDTO);

                dateAndCountHours.put(start.getTime(),workingHours);
            } else if (period.MONTH.equals(period)) {

                dayOfMonth = start.get(Calendar.DAY_OF_MONTH);
                dayInMonth = start.getActualMaximum(Calendar.DAY_OF_MONTH);
                temp = start;

                //start of period
                if(dayOfMonth != dayInMonth && (Days.daysBetween(new DateTime(start), new DateTime(end.getTime())).getDays() >= dayInMonth)) {
                    remainDaysToEndPeriod = dayInMonth - dayOfMonth;
                    date = new Date(temp.getTime().getTime());
                    //end of period
                }  else if(Days.daysBetween(new DateTime(start), new DateTime(end.getTime())).getDays() < dayInMonth) {
                    remainDaysToEndPeriod = Days.daysBetween(new DateTime(start), new DateTime(end)).getDays();
                    date = new Date(temp.getTime().getTime() + TimeUnit.DAYS.toMillis(1));
                    //middle of period
                }  else {

                    date = new Date(temp.getTime().getTime() + TimeUnit.DAYS.toMillis(1));
                    Calendar dateCal= Calendar.getInstance();
                    dateCal.setTime(date);
                    dayInMonth =  dateCal.getActualMaximum(Calendar.DAY_OF_MONTH);
                    remainDaysToEndPeriod = dayInMonth;
                }


                countSnapshots = getCountSnapshots(start.getTime(),remainDaysToEndPeriod,userEntity);
                workingHours = ((double)(countSnapshots * countMinutesBetweenTwoSnapshots)) / countMinutesInHour;
                start.add(Calendar.DATE, remainDaysToEndPeriod);

                periodDTO = periodToDTO.apply(date,start.getTime());
                periodDTOs.add(periodDTO);

                dateAndCountHours.put(start.getTime(),workingHours);

            }
        }

        return dateAndCountHours;
    }



    private int getCountSnapshots(Date start, Integer period, UserEntity userEntity) {
        int countSnapshots = 0;
        Date end = new Date(start.getTime() + TimeUnit.DAYS.toMillis(period));
        countSnapshots = snapshotRepository.countByUserIdAndPeriod(userEntity.getId(), start, end);
        return countSnapshots;
    }

}

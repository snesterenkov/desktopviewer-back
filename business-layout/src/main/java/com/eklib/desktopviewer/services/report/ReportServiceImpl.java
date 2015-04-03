package com.eklib.desktopviewer.services.report;

import com.eklib.desktopviewer.convertor.todto.report.WorkDiaryToDTO;
import com.eklib.desktopviewer.convertor.todto.security.UserToDTO;
import com.eklib.desktopviewer.dto.report.WorkDiaryDTO;
import com.eklib.desktopviewer.dto.security.UserDTO;
import com.eklib.desktopviewer.persistance.model.enums.PeriodEnum;
import com.eklib.desktopviewer.persistance.model.security.UserEntity;
import com.eklib.desktopviewer.persistance.repository.security.UserRepository;
import com.eklib.desktopviewer.persistance.repository.snapshot.SnapshotRepository;
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
    private UserToDTO userToDTO;

    public List<WorkDiaryDTO> getWorkingHoursByTimePeriod(PeriodEnum period, Date startDate, Date endDate) {
        List<WorkDiaryDTO> workDiaryDTOs = new ArrayList<WorkDiaryDTO>();

        List<UserEntity> userEntities = userRepository.findAll();
        Map<Date, Double> countHoursOnDate = new HashMap<Date, Double>();

        for(UserEntity userEntity:userEntities) {
            countHoursOnDate = calculateWorkingHoursOnDate(period, userEntity, startDate, endDate);
            UserDTO user = userToDTO.apply(userEntity);
            WorkDiaryDTO workDiaryDTO = workDiaryToDTO.apply(countHoursOnDate,user);
            workDiaryDTOs.add(workDiaryDTO);
        }

        return workDiaryDTOs;
    }


    private Map<Date, Double> calculateWorkingHoursOnDate(PeriodEnum period, UserEntity userEntity, Date startDate, Date endDate) {
        int countSnapshots = 0;
        double workingHours = 0;
        Map<Date, Double> dateAndCountHours = new HashMap<Date, Double>();
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        Calendar temp = Calendar.getInstance();

        while(!start.after(end)) {
            countSnapshots = getCountSnapshots(start.getTime(),period,userEntity);
            workingHours = ((double)(countSnapshots * countMinutesBetweenTwoSnapshots)) / countMinutesInHour;
            dateAndCountHours.put(start.getTime(),workingHours);
            start.add(Calendar.DATE, period.getDurationDays());
        }

        return dateAndCountHours;
    }

    private int getCountSnapshots(Date start, PeriodEnum period, UserEntity userEntity) {
        int countSnapshots = 0;
        Date end = new Date(start.getTime() + TimeUnit.DAYS.toMillis(period.getDurationDays()));
        //start.setTime(end.getTime());
        countSnapshots = snapshotRepository.countByUserIdAndPeriod(userEntity.getId(), start, end);
        return countSnapshots;
    }

}

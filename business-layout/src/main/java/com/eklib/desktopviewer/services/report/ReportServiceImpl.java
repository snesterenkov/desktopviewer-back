package com.eklib.desktopviewer.services.report;

import com.eklib.desktopviewer.convertor.todto.report.WorkDiaryToDTO;
import com.eklib.desktopviewer.convertor.todto.security.UserToDTO;
import com.eklib.desktopviewer.dto.report.WorkDiaryDTO;
import com.eklib.desktopviewer.dto.security.UserDTO;
import com.eklib.desktopviewer.persistance.model.security.UserEntity;
import com.eklib.desktopviewer.persistance.repository.security.UserRepository;
import com.eklib.desktopviewer.persistance.repository.snapshot.SnapshotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by alex on 4/1/2015.
 */
@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    @Autowired
    private SnapshotRepository snapshotRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkDiaryToDTO workDiaryToDTO;

    @Autowired
    private UserToDTO userToDTO;

    public List<WorkDiaryDTO> getWorkingHoursByTimePeriod(Date startDate, Date endDate) {
        List<WorkDiaryDTO> workDiaryDTOs = new ArrayList<WorkDiaryDTO>();

        List<UserEntity> userEntities = userRepository.findAll();
        Map<Date, Double> countHoursOnDate = new HashMap<Date, Double>();

        for(UserEntity userEntity:userEntities) {
            countHoursOnDate = calculateWorkingHoursOnDate(userEntity, startDate, endDate);
            UserDTO user = userToDTO.apply(userEntity);
            WorkDiaryDTO workDiaryDTO = workDiaryToDTO.apply(countHoursOnDate,user);
            workDiaryDTOs.add(workDiaryDTO);
        }

        return workDiaryDTOs;
    }


    private Map<Date, Double> calculateWorkingHoursOnDate(UserEntity userEntity, Date startDate, Date endDate) {
        int countSnapshots = 0;
        double workingHours = 0;
        Map<Date, Double> dateAndCountHours = new HashMap<Date, Double>();
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);

        while(!start.after(end)) {
            countSnapshots = snapshotRepository.countByUserIdAndDate(userEntity.getId(), start.getTime());
            workingHours = (countSnapshots * 10) / 60;
            dateAndCountHours.put(start.getTime(), workingHours);
            start.add(Calendar.DATE, 1);
        }

        return dateAndCountHours;
    }

}

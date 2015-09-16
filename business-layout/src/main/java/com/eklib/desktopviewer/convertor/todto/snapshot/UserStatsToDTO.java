package com.eklib.desktopviewer.convertor.todto.snapshot;

import com.eklib.desktopviewer.dto.snapshot.UserStatsDTO;
import com.google.common.base.Function;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by s.sheman on 15.09.2015.
 */
@Component
public class UserStatsToDTO implements Function<Object[], UserStatsDTO> {

    @Override
    public UserStatsDTO apply(Object[] objects) {
        if(objects == null){
            return null;
        }
        UserStatsDTO userStatsDTO = new UserStatsDTO();
        userStatsDTO.setUserId((Long) objects[0]);
        userStatsDTO.setStartTime((Date) objects[1]);
        userStatsDTO.setSnapshotsCount((Long) objects[2] );

        return userStatsDTO;
    }
}

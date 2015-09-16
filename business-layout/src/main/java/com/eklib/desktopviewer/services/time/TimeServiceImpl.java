package com.eklib.desktopviewer.services.time;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by s.sheman on 16.09.2015.
 */
@Service
@Transactional
public class TimeServiceImpl implements TimeService {

    @Override
    public Long getCurrentTime() {
        return new Date().getTime();
    }
}

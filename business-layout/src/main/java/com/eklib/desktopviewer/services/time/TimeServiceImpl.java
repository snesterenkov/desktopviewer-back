package com.eklib.desktopviewer.services.time;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by s.sheman on 16.09.2015.
 */
@Service
@Transactional
public class TimeServiceImpl implements TimeService {

    @Override
    public String getCurrentTime() {
        return DateTime.now().toString("dd.MM.yyyy hh:mm:ss");
    }
}

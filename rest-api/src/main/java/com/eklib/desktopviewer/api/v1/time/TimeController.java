package com.eklib.desktopviewer.api.v1.time;

import com.eklib.desktopviewer.services.time.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by s.sheman on 16.09.2015.
 */
@RestController
@RequestMapping("/time")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @RequestMapping(method = RequestMethod.GET, headers="Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getCurrentTime() {
        return timeService.getCurrentTime();
    }

}

package com.eklib.desktopviewer.api.v1.snapshot;


import com.eklib.desktopviewer.dto.snapshot.SnapshotDTO;
import com.eklib.desktopviewer.services.snapshot.SnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by vadim on 18.12.2014.
 */
@RestController
@RequestMapping("/snapshot")
public class SnapshotController {

    @Autowired
    private SnapshotService snapshotService;

    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.POST, headers="Accept=application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public SnapshotDTO createSnapshot(@RequestBody SnapshotDTO snapshotDTO, @RequestParam(value = "client", required = false) String client){
        return snapshotService.insert(snapshotDTO, client);
    }

    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET, headers="Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<String> getFileName(@RequestParam(value = "client", required = false) String client){
        return snapshotService.getFileName(client);
    }

    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(value= "/user/snapshots/{id}", method = RequestMethod.GET, headers="Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<SnapshotDTO> findByUser(@PathVariable("id") Long userId){
        return snapshotService.findByUser(userId);
    }
}

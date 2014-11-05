package com.eklib.desktopviewer.api.v1.security;

import com.eklib.desktopviewer.dto.security.RoleDTO;
import com.eklib.desktopviewer.services.security.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by vadim on 29.10.2014.
 */
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private UserServices userServices;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET,  headers="Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Set<RoleDTO> findAll(@RequestParam(value = "userId") Long userId){
        return userServices.getRolesById(userId);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.PUT, headers="Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Set<RoleDTO> update(@RequestParam(value = "userId") Long userId, @RequestBody Set<RoleDTO> updateUser) {
       return userServices.updateRolesById(userId, updateUser);
    }
}

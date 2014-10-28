package com.eklib.desktopviewer.api.v1;

import com.eklib.desktopviewer.dto.UserDTO;
import com.eklib.desktopviewer.dto.UserDetailDTO;
import com.eklib.desktopviewer.dto.security.RoleDTO;
import com.eklib.desktopviewer.persistance.model.User;
import com.eklib.desktopviewer.services.security.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;



    @RequestMapping(method = RequestMethod.POST, headers="Accept=application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    UserDTO createUser(@RequestBody UserDetailDTO newUser){
        UserDTO user = userServices.createUser(newUser);
        return user;
    }


    @RequestMapping(method = RequestMethod.GET,  headers="Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<UserDTO> findAll(){
        return new ArrayList<UserDTO>(userServices.findAll());
    }

    @RequestMapping(value= "/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody UserDTO update(@PathVariable("id") Long userId, @RequestBody UserDTO updateUser){
        UserDTO user = userServices.update(userId,updateUser);
        return user;
    }

    @RequestMapping(value= "/{id}", method = RequestMethod.DELETE, headers="Accept=application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long userId){
        userServices.delete(userId);
    }

    @RequestMapping(value= "/{id}", method = RequestMethod.GET, headers="Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody UserDTO findById(@PathVariable("id") Long userId){
        return userServices.findById(userId);
    }
}

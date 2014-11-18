package com.eklib.desktopviewer.api.v1.security;

import com.eklib.desktopviewer.dto.security.UserDTO;
import com.eklib.desktopviewer.dto.security.UserDetailDTO;
import com.eklib.desktopviewer.services.security.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @ResponseBody
    public UserDTO createUser(@RequestBody UserDetailDTO newUser){
        return userServices.createUser(newUser);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET,  headers="Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDTO> findAll(){
        return new ArrayList<UserDTO>(userServices.findAll());
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value= "/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDTO update(@PathVariable("id") Long userId, @RequestBody UserDTO updateUser){
        return userServices.update(userId,updateUser);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value= "/{id}", method = RequestMethod.DELETE, headers="Accept=application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long userId){
        userServices.delete(userId);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value= "/{id}", method = RequestMethod.GET, headers="Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDTO findById(@PathVariable("id") Long userId){
        return userServices.findById(userId);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value= "/authorized", method = RequestMethod.GET, headers="Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDetailDTO findByLogin(@RequestParam(value = "login") String login, @RequestParam(value = "client", required = false) String client ){
        if(login.equals(client)) {
            return userServices.getAutorizedUser(login);
        }
        throw new IllegalAccessError("You can not authorized to another user");
    }
}

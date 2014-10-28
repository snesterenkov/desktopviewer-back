package com.eklib.desktopviewer.api.v1;

import com.eklib.desktopviewer.api.v1.model.ListUser;
import com.eklib.desktopviewer.dto.security.RoleDTO;
import com.eklib.desktopviewer.persistance.model.User;
import com.eklib.desktopviewer.services.security.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;



    @RequestMapping(method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<User> insert(@RequestBody User newUser){
        User user = userServices.insert(newUser);
        ResponseEntity<User> entity = new ResponseEntity<User>(user, HttpStatus.CREATED);
        return entity;
    }


    @Secured({ RoleDTO.ROLE_DESK_USER})
    @RequestMapping(method = RequestMethod.GET,  headers="Accept=application/json")
    public @ResponseBody ResponseEntity<ListUser> findAll(){
        ListUser listUser = new ListUser();
        listUser.setUserList(new ArrayList<User>(userServices.findAll()));
        ResponseEntity<ListUser> entity = new ResponseEntity<ListUser>(listUser, HttpStatus.OK);
        return  entity;
    }

    @RequestMapping(value= "/{id}", method = RequestMethod.PUT, headers="Accept=application/json")
    public ResponseEntity<User> update(@PathVariable("id") Long userId, @RequestBody User updateUser){
        updateUser.setId(userId);
        User user = userServices.update(updateUser);
        ResponseEntity<User> entity = new ResponseEntity<User>(user, HttpStatus.CREATED);
        return entity;
    }

    @RequestMapping(value= "/{id}", method = RequestMethod.DELETE, headers="Accept=application/json")
    public ResponseEntity<User> delete(@PathVariable("id") Long userId){
        userServices.delete(userId);
        ResponseEntity<User> entity = new ResponseEntity<User>(HttpStatus.OK);
        return entity;
    }

    @RequestMapping(value= "/{id}", method = RequestMethod.GET, headers="Accept=application/json")
    public @ResponseBody User findById(@PathVariable("id") Long userId ){
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userServices.findById(userId);
    }


}

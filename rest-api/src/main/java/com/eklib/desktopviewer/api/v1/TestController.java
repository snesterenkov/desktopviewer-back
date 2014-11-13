package com.eklib.desktopviewer.api.v1;

import com.eklib.desktopviewer.dto.TestDTO;
import com.eklib.desktopviewer.services.TestServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxim on 13.11.2014.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestServices testServices;

//    @PreAuthorize("isAuthenticated()")
    @RequestMapping(headers = "Accept=application/json", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public TestDTO createTest(@RequestBody TestDTO testDTO){
        return  testServices.insert(testDTO);
    }

//    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/{id}", headers = "Accept=application/json", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public TestDTO getTest(@PathVariable("id") Long id){
        return testServices.findById(id);
    }

//    @PreAuthorize("isAuthenticated()")
    @RequestMapping(headers = "Accept=application/json", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<TestDTO> findAll(){
        return (ArrayList<TestDTO>)testServices.findAll();
    }
}

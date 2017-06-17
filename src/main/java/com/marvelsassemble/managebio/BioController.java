package com.marvelsassemble.managebio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by hemantv on 17/6/17.
 */
@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "*")
@RequestMapping("/bio")
public class BioController {

    @Autowired
    BioService bioService;

    @RequestMapping(value="/all/{squad}", method = RequestMethod.GET)
    public void getAllUser(@PathVariable String squad, HttpSession session, HttpServletResponse response){
        bioService.getAllUserService(squad, session, response);
    }

    @RequestMapping(value="/{squad}/{uname}", method = RequestMethod.GET)
    public String getBio(@PathVariable String squad, @PathVariable String uname, HttpSession session, HttpServletResponse response){
        return bioService.getAllBioService(squad, uname, session, response);
    }

}

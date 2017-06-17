package com.marvelsassemble.managebio;

import com.marvelsassemble.authenticate.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by hemantv on 17/6/17.
 */
@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "*")
@RequestMapping("/bio")
public class BioController {

    @Autowired
    private BioService bioService;

    @RequestMapping(value="/all/{squad}", method = RequestMethod.GET)
    public List<User> getAllUser(@PathVariable String squad, HttpSession session, HttpServletResponse response){
        return bioService.getAllUserService(squad, session, response);
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String getBio(HttpSession session, HttpServletResponse response){
        return bioService.getAllBioService(session, response);
    }

}

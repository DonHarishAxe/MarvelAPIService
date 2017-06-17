package com.marvelsassemble.authenticate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by hemantv on 16/6/17.
 */

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;

    @RequestMapping(value="/register/avenger", method = RequestMethod.POST)
    public void createAvenger(@RequestBody User user, HttpServletResponse response){
        userAuthService.createAvenger(user, response);
    }

    @RequestMapping(value="/register/xmen", method = RequestMethod.POST)
    public void createXmen(@RequestBody User user, HttpServletResponse response){
        userAuthService.createXmen(user, response);
    }

    @RequestMapping(value="/login/avenger", method = RequestMethod.POST)
    public String loginAveneger(@RequestBody Map<String, String> body, HttpSession session, HttpServletResponse response){
        String uname = body.get("username");
        String pass = body.get("password");
        if(userAuthService.AuthenticateAvenger(uname, pass, session, response)){
            return "Yes";
        }
        else{
            return "No";
        }
    }

    @RequestMapping(value="/login/xmen", method = RequestMethod.POST)
    public String loginXmen(@RequestBody Map<String, String> body, HttpSession session, HttpServletResponse response){
        String uname = body.get("username");
        String pass = body.get("password");
        if(userAuthService.AuthenticateXmen(uname, pass, session, response)){
            return "Yes";
        }
        else{
            return "No";
        }
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public void logoutUser(HttpSession session, HttpServletResponse response){
        userAuthService.logoutService(session, response);
    }

}

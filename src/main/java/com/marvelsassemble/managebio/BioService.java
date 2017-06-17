package com.marvelsassemble.managebio;

import com.marvelsassemble.authenticate.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hemantv on 17/6/17.
 */
@Transactional
@Service
public class BioService {

    @Autowired
    BioRepository bioRepository;

    public List<User> getAllUserService(String squad, HttpSession session, HttpServletResponse response) {
        if(session.getAttribute("user")!=null){
            response.setStatus(200);
            if(squad.equals("avengers"))
                return bioRepository.findByIsAvenger(true);
            else if(squad.equals("xmen"))
                return bioRepository.findByIsXmen(true);
            else
                return null;
        }
        else{
            User dummy = new User();
            dummy.setUname("Batman");
            dummy.setBio("I am here beacuse i am batman ! Don't care about marvel or DC");
            dummy.setXmen(false);
            dummy.setAvenger(false);
            dummy.setPowers("I am a rich philanthropist with gadgets !");
            response.setStatus(403);
            return Arrays.asList(dummy);
        }
    }

    public String getAllBioService(String squad, String uname, HttpSession session, HttpServletResponse response) {
        if(session.getAttribute("user")!=null){
            response.setStatus(200);
            return bioRepository.findByUname(uname).getBio();
        }
        else{
            response.setStatus(403);
            return "<h1>Log In First, Then snoop araound my team</h1>";
        }
    }
}

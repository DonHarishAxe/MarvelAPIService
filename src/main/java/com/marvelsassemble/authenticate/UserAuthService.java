package com.marvelsassemble.authenticate;

import com.marvelsassemble.utils.BCrypt;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

/**
 * Created by hemantv on 16/6/17.
 */
@Transactional
@Service
public class UserAuthService {

    @Autowired
    private UserAuthRepository userAuthRepository;
    @Autowired
    private Environment env;

    public void createAvenger(User user, HttpServletResponse response ) {
        if (!userAuthRepository.exists(user.getUname())) {
            user.setPassword(BCrypt.hashpw(user.getPassword(),env.getProperty("hashWithSalt")));
            user.setAvenger(true);
            user.setXmen(false);
            user.setBio(cleanHtml(user.getBio()));
            userAuthRepository.save(user);
            response.setStatus(201);
        }
        else if(userAuthRepository.exists(user.getUname()) && userAuthRepository.findByUname(user.getUname()).isXmen()){
            if(!userAuthRepository.findByUname(user.getUname()).isAvenger()){
                User xman = userAuthRepository.findByUname(user.getUname());
                xman.setAvenger(true);
                user.setBio(cleanHtml(user.getBio()));
                userAuthRepository.save(xman);
                response.setStatus(201);
            }
            else{
                response.setStatus(403);
            }
        }
        else if(userAuthRepository.exists(user.getUname()) && userAuthRepository.findByUname(user.getUname()).isAvenger()){
            response.setStatus(403);
        }
        else{
            response.setStatus(403);
        }
    }

    public void createXmen(User user, HttpServletResponse response) {
        if (!userAuthRepository.exists(user.getUname())) {
            user.setPassword(BCrypt.hashpw(user.getPassword(),env.getProperty("hashWithSalt")));
            user.setAvenger(false);
            user.setXmen(true);
            user.setBio(cleanHtml(user.getBio()));
            userAuthRepository.save(user);
            response.setStatus(201);
        }
        else if(userAuthRepository.exists(user.getUname()) && userAuthRepository.findByUname(user.getUname()).isAvenger()){
            if(!userAuthRepository.findByUname(user.getUname()).isXmen()) {
                User avenger = userAuthRepository.findByUname(user.getUname());
                avenger.setXmen(true);
                user.setBio(cleanHtml(user.getBio()));
                userAuthRepository.save(avenger);
                response.setStatus(201);
            }
            else{
                response.setStatus(403);
            }
        }
        else if(userAuthRepository.exists(user.getUname()) && userAuthRepository.findByUname(user.getUname()).isXmen()){
            response.setStatus(403);
        }
        else{
            response.setStatus(403);
        }
    }


    public void logoutService(HttpSession session, HttpServletResponse response) {
        if(session.getAttribute("user")!=null) {
            session.invalidate();
            response.setStatus(302);
            //response.setHeader("Location", "http://10.128.3.120:8000/index.html");
        }
        else{
            response.setStatus(400);
        }
    }

    public boolean AuthenticateAvenger(String uname, String pass, HttpSession session, HttpServletResponse response) {
        if(!userAuthRepository.exists(uname)) {
            response.setStatus(404);
            return false;
        }
        User user = userAuthRepository.findByUname(uname);
        if(!user.isAvenger()){
            response.setStatus(409);
            return false;
        }
        else if(BCrypt.checkpw(pass,user.getPassword())){
            session.setAttribute("user",user.getUname());
            session.setMaxInactiveInterval(0);
            response.setStatus(202);
            return true;
        }else{
            response.setStatus(403);
            return false;
        }
    }

    public boolean AuthenticateXmen(String uname, String pass, HttpSession session, HttpServletResponse response) {
        if(!userAuthRepository.exists(uname)) {
            response.setStatus(404);
            return false;
        }
        User user = userAuthRepository.findByUname(uname);
        if(!user.isXmen()){
            response.setStatus(409);
            return false;
        }
        else if(BCrypt.checkpw(pass,user.getPassword())){
            session.setAttribute("user",user.getUname());
            response.setStatus(202);
            return true;
        }else{
            response.setStatus(403);
            return false;
        }
    }

    private String cleanHtml(String unsafe){
        String safe;
        try {
            safe = Jsoup.clean(unsafe, Whitelist.basic());
        }
        catch(Exception E){
            safe = "<html><h1>You mess with other, you pay</h1></html>";
        }
        return safe;
    }
}

package com.marvelsassemble.authenticate;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
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
    UserAuthRepository userAuthRepository;

    public void createAvenger(User user, HttpServletResponse response ) {
        if (!userAuthRepository.exists(user.getUname())) {
            String pass = user.getPassword();
            user.setPassword("" + pass.hashCode());
            user.setAvenger(true);
            user.setXmen(false);
            user.setBio(cleanHtml(user.getBio()));
            userAuthRepository.save(user);
            response.setStatus(201);
        }
        else if(userAuthRepository.exists(user.getUname()) && user.isXmen()){
            if(!user.isAvenger()){
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
        else if(userAuthRepository.exists(user.getUname()) && user.isAvenger()){
            response.setStatus(403);
        }
        else{
            response.setStatus(403);
        }
    }

    public void createXmen(User user, HttpServletResponse response) {
        if (!userAuthRepository.exists(user.getUname())) {
            String pass = user.getPassword();
            user.setPassword("" + pass.hashCode());
            user.setAvenger(false);
            user.setXmen(true);
            user.setBio(cleanHtml(user.getBio()));
            userAuthRepository.save(user);
            response.setStatus(201);
        }
        else if(userAuthRepository.exists(user.getUname()) && user.isAvenger()){
            if(!user.isXmen()) {
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
        else if(userAuthRepository.exists(user.getUname()) && user.isXmen()){
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
            response.setHeader("Location", "http://10.128.3.120:8000/index.html");
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
        else if(user.getPassword().equals(""+pass.hashCode())){
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
        else if(user.getPassword().equals(""+pass.hashCode())){
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

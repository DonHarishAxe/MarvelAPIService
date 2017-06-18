package com.marvelsassemble.squadtodo;

import com.marvelsassemble.authenticate.User;
import com.marvelsassemble.authenticate.UserAuthRepository;
import com.marvelsassemble.personaltodo.PersonalToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hemantv on 16/6/17.
 */
@Transactional
@Service
public class SquadToDoService {

    @Autowired
    private SquadToDoRepository squadToDoRepository;
    @Autowired
    private UserAuthRepository userAuthRepository;

    public List<SquadToDo> getAll(HttpSession session, HttpServletResponse response) {

        if(session.getAttribute("user")!=null) {
            response.setStatus(200);
            String name = (String)session.getAttribute("user");
            User user = userAuthRepository.findByUname(name);
            List<SquadToDo> resp = new ArrayList<>();
            if(user.isAvenger())
                resp.addAll(squadToDoRepository.findBySquad("avengers"));
            if(user.isXmen())
                resp.addAll(squadToDoRepository.findBySquad("xmen"));
            return resp;
        }
        else{
            response.setStatus(403);
            SquadToDo dummy = new SquadToDo();
            dummy.setTodoItem("Get out Now! Sef Destruct in T-10 seconds");
            dummy.setSetBy("Batman");
            dummy.setActive(false);
            dummy.setSquad("Rogue-LoneRanger");
            return Arrays.asList(dummy);
        }
    }

    public void createTodo(SquadToDo todoitem, String squad, HttpSession session, HttpServletResponse response) {

        if(session.getAttribute("user")!=null && (squad.equals("avengers") || squad.equals("xmen"))) {
            String uname = (String)session.getAttribute("user");
            todoitem.setSetBy(uname);
            todoitem.setSquad(squad);
            todoitem.setActive(true);
            squadToDoRepository.save(todoitem);
            response.setStatus(201);
        }
        else{
            response.setStatus(403);
        }
    }

    public void todoComplete(int id, HttpSession session, HttpServletResponse response){
        if(session.getAttribute("user")!=null) {
            SquadToDo todoUpdate = squadToDoRepository.findById(id);
            todoUpdate.setActive(false);
            squadToDoRepository.save(todoUpdate);
            response.setStatus(202);
        }
        else{
            response.setStatus(403);
        }
    }
}

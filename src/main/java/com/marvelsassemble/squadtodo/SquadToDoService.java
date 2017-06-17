package com.marvelsassemble.squadtodo;

import com.marvelsassemble.personaltodo.PersonalToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
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

    public List<SquadToDo> getAll(String squad, HttpSession session, HttpServletResponse response) {
        //check squad string adn apply filte
        if(session.getAttribute("user")!=null) {
            response.setStatus(200);
            return squadToDoRepository.findBySquad(squad);
        }
        else{
            response.setStatus(403);
            SquadToDo dummy = new SquadToDo();
            dummy.setTodoItem("Get out Now! Sef Destruct in T-10 seconds");
            dummy.setSetBy("Evil genius Hemant");
            dummy.setActive(false);
            dummy.setSquad("Rogue-LoneRanger");
            return Arrays.asList(dummy);
        }
    }

    public void createTodo(SquadToDo todoitem, String uname, String squad, HttpSession session, HttpServletResponse response) {
        //check squad string and apply filter
        //get user object by query with id store in user variable
        if(session.getAttribute("user")!=null) {
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

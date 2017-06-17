package com.marvelsassemble.personaltodo;


import com.marvelsassemble.authenticate.User;
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
public class PersonalToDoService {

    @Autowired
    private PersonalToDoRepository personalToDoRepository;

    public List<PersonalToDo> getAllTodo(String uname, HttpSession session, HttpServletResponse response) {

        if(session.getAttribute("user")!=null) {
            response.setStatus(200);
            return personalToDoRepository.findBySetBy(uname);
        }
        else{
            response.setStatus(403);
            PersonalToDo dummy = new PersonalToDo();
            dummy.setTodoItem("Get out Now! Self Destruct in T-10 seconds");
            dummy.setSetBy("Evil genius Hemant");
            dummy.setActive(false);
            return Arrays.asList(dummy);
        }
    }

    public void createTodo(PersonalToDo todoitem, String uname, HttpSession session, HttpServletResponse response) {

        if(session.getAttribute("user")!=null) {
            todoitem.setSetBy(uname);
            todoitem.setActive(true);
            personalToDoRepository.save(todoitem);
            response.setStatus(201);
        }
        else{
            response.setStatus(403);
        }
    }

    public void todoComplete(int id, HttpSession session, HttpServletResponse response){

        if(session.getAttribute("user")!=null) {
            PersonalToDo todoUpdate = personalToDoRepository.findById(id);
            todoUpdate.setActive(false);
            personalToDoRepository.save(todoUpdate);
            response.setStatus(202);
        }
        else{
            response.setStatus(403);
        }
    }
}

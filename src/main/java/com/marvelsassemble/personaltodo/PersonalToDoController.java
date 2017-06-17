package com.marvelsassemble.personaltodo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by hemantv on 16/6/17.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/personal")
@CrossOrigin(origins = "*")
public class PersonalToDoController {

    @Autowired
    private PersonalToDoService personalTodoService;


    @RequestMapping(value="/{uname}", method = RequestMethod.GET)
    public List<PersonalToDo> getPersonalTodo(@PathVariable String uname, HttpSession session, HttpServletResponse response){
        //cleanse the path variable
        return personalTodoService.getAllTodo(uname, session, response);
    }

    @RequestMapping(value="/{uname}", method = RequestMethod.POST)
    public void createPersonaLToDo(@RequestBody PersonalToDo todoitem, @PathVariable String uname, HttpSession session, HttpServletResponse response){
        //clease the body and path vairable for security
        personalTodoService.createTodo(todoitem, uname, session, response);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void completePersonalToDo(@PathVariable int id, HttpSession session, HttpServletResponse response){
        //clease the body and path vairable for security
        personalTodoService.todoComplete(id, session, response);
    }

}

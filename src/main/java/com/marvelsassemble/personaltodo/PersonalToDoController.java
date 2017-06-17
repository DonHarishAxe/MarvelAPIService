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


    @RequestMapping(value="/", method = RequestMethod.GET)
    public List<PersonalToDo> getPersonalTodo(HttpSession session, HttpServletResponse response){
        return personalTodoService.getAllTodo(session, response);
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public void createPersonaLToDo(@RequestBody PersonalToDo todoitem, HttpSession session, HttpServletResponse response){
        personalTodoService.createTodo(todoitem, session, response);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void completePersonalToDo(@PathVariable int id, HttpSession session, HttpServletResponse response){
        personalTodoService.todoComplete(id, session, response);
    }

}

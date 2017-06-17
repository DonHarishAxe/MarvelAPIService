package com.marvelsassemble.squadtodo;

/**
 * Created by hemantv on 16/6/17.
 */

import com.marvelsassemble.personaltodo.PersonalToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/squad")
@CrossOrigin(origins = "*")
public class SquadToDoController {

    @Autowired
    private SquadToDoService squadTodoService;

    @RequestMapping(value="/{squad}", method = RequestMethod.GET)
    public List<SquadToDo> getSquadTodo(@PathVariable String squad, HttpSession session, HttpServletResponse response){
        return squadTodoService.getAll(session, response);
    }

    @RequestMapping(value="/{squad}/{uname}", method = RequestMethod.POST)
    public void createSquadToDo(@RequestBody SquadToDo todoitem, @PathVariable String uname, @PathVariable String squad, HttpSession session, HttpServletResponse response){
        squadTodoService.createTodo(todoitem, uname, squad, session, response);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateSquadToDo(@PathVariable int id, HttpSession session, HttpServletResponse response){
        //clease the body and path vairable for security
        squadTodoService.todoComplete(id, session, response);
    }

}

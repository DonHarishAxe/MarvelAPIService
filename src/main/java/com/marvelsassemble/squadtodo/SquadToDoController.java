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
@CrossOrigin(origins = "*")
@RequestMapping("/squad")
//@CrossOrigin(origins = "*")
public class SquadToDoController {

    @Autowired
    private SquadToDoService squadTodoService;

    @RequestMapping(value="/{squad}", method = RequestMethod.GET)
    public List<SquadToDo> getSquadTodo(@PathVariable String squad, HttpSession session, HttpServletResponse response){
        //cleanse the path variable
        //make sure path variabl eis either avenger or xmen nothin gelse
        return squadTodoService.getAll(session, response);
    }

    @RequestMapping(value="/{squad}/{uname}", method = RequestMethod.POST)
    public void createSquadToDo(@RequestBody SquadToDo todoitem, @PathVariable String uname, @PathVariable String squad, HttpSession session, HttpServletResponse response){
        //clease the body and path vairable for security,
        //make sure path variabl eis either avenger or xmen nothin gels
        squadTodoService.createTodo(todoitem, uname, squad, session, response);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateSquadToDo(@PathVariable int id, HttpSession session, HttpServletResponse response){
        //clease the body and path vairable for security
        squadTodoService.todoComplete(id, session, response);
    }

}

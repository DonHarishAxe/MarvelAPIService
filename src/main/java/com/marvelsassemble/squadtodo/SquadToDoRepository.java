package com.marvelsassemble.squadtodo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hemantv on 16/6/17.
 */
public interface SquadToDoRepository extends CrudRepository<SquadToDo, String> {

    public List<SquadToDo> findBySquad(String squad);
    public SquadToDo findById(int id);

}

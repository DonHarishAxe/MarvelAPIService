package com.marvelsassemble.personaltodo;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hemantv on 16/6/17.
 */
public interface PersonalToDoRepository extends CrudRepository<PersonalToDo, String> {

    public List<PersonalToDo> findBySetBy(String user);
    public PersonalToDo findById(int id);



}

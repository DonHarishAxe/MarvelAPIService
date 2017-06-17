package com.marvelsassemble.personaltodo;



import com.marvelsassemble.authenticate.User;

import javax.persistence.*;

/**
 * Created by hemantv on 16/6/17.
 */
@Entity
@Table(name = "personal_to_do")
public class PersonalToDo {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int id;
    @Column(name = "todoItem")
    private String todoItem;
    @Column(name = "setBy")
    private String setBy;
    @Column(name = "isActive")
    private boolean isActive;

    public PersonalToDo() {

    }

    public PersonalToDo( String todoItem, String user) {
        this.todoItem = todoItem;
        this.setBy = user;
        this.isActive = true;
    }

    public int getId() {
        return id;
    }

    public String getTodoItem() {
        return todoItem;
    }

    public void setTodoItem(String todoItem) {
        this.todoItem = todoItem;
    }

    public String getSetBy() {
        return setBy;
    }

    public void setSetBy(String setBy) {
        this.setBy = setBy;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}




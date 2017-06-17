package com.marvelsassemble.squadtodo;

import org.springframework.boot.autoconfigure.security.SecurityProperties;

import javax.persistence.*;

/**
 * Created by hemantv on 16/6/17.
 */
@Entity
@Table(name = "squad_to_do")
public class SquadToDo {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int id;
    @Column(name = "todoItem")
    private String todoItem;
    @Column(name = "setBy")
    private String setBy;
    @Column(name = "squad")
    private String squad;
    @Column(name = "isActive")
    private boolean isActive;

    public SquadToDo(){

    }

    public SquadToDo(String todoItem, String setBy, String squad) {
        this.todoItem = todoItem;
        this.setBy = setBy;
        this.squad = squad;
        this.isActive = true;
    }

    public String getSquad() {
        return squad;
    }

    public void setSquad(String squad) {
        this.squad = squad;
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

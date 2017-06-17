package com.marvelsassemble.authenticate;

import javax.persistence.*;

/**
 * Created by hemantv on 16/6/17.
 */
@Entity
@Table(name = "user")
public class User {

    @Column(name = "email")
    private String email; //make sure unique
    @Column(name = "fname")
    private String fname;
    @Column(name = "lname")
    private String lname;
    @Id
    @Column(name = "uname")
    private String uname; //make sure unique
    @Column(name = "powers")
    private String powers; //check if list is required
    @Column(name = "isAvenger")
    private boolean isAvenger;
    @Column(name = "isXmen")
    private boolean isXmen;


    @Column(name = "bio")
    private String bio; //check fo rthe correct datatype
    @Column(name="password")
    private String password;

    //manage constructors

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPowers() {
        return powers;
    }

    public void setPowers(String powers) {
        this.powers = powers;
    }

    public boolean isAvenger() {
        return isAvenger;
    }

    public void setAvenger(boolean avenger) {
        isAvenger = avenger;
    }

    public boolean isXmen() {
        return isXmen;
    }

    public void setXmen(boolean xmen) {
        isXmen = xmen;
    }

    public String getBio() {
        return bio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}

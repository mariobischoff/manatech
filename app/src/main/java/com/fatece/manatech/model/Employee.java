package com.fatece.manatech.model;

import java.io.Serializable;

public class Employee implements Serializable {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String function;
    private Integer id_time;

    public Employee(String firstName, String lastName, String email, String username, String password, String function, Integer id_time) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.function = function;
        this.id_time = id_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String func) {
        this.function = func;
    }

    public Integer getId_time() {
        return id_time;
    }

    public void setId_time(Integer id_time) {
        this.id_time = id_time;
    }
}

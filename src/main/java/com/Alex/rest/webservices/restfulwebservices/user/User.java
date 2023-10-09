package com.Alex.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class User {

    public User() {
    }

    public User(Integer id, String name, LocalDateTime birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    private Integer id;
    private String name;
    private LocalDateTime birthDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}

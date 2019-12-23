package com.example.mylivedata2;

public class Person {
    private String name, lastname, hobby;
    public Person()
    {
    }
    public Person(String name, String lastname, String hobby) {
        this.name = name;
        this.lastname = lastname;
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}

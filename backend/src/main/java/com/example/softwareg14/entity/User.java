package com.example.softwareg14.entity;


import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String username;
    private String password;
    private String email;
    private Role role;

    public User() {
    }
    public User(String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email= email;
    }

}

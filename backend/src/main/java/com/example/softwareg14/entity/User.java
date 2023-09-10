package com.example.softwareg14.entity;


import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String username;
    private String password;
    private String email;

    public User() {
    }

}

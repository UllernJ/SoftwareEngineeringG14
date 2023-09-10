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

    public boolean validate() {
        if(this.name == null || this.name.isEmpty()) {
            return false;
        }
        if(this.username == null || this.username.isEmpty()) {
            return false;
        }
        if(this.password == null || this.password.isEmpty()) {
            return false;
        }
        if(this.email == null || this.email.isEmpty()) {
            return false;
        }
        return true;
    }

}

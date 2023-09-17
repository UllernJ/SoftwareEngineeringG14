package com.example.softwareg14.map.user;

public class UserRequest {
    public String email;
    public String name;
    public String username;
    public String password;
    public int id;

    public boolean validate() {
        if(email == null || email.isEmpty()) {
            return false;
        }
        if(name == null || name.isEmpty()) {
            return false;
        }
        if(username == null || username.isEmpty()) {
            return false;
        }
        if(password == null || password.isEmpty()) {
            return false;
        }
        return true;
    }
}

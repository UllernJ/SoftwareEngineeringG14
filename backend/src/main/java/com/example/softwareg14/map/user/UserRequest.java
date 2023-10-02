package com.example.softwareg14.map.user;

import java.util.ArrayList;
import java.util.List;
import com.example.softwareg14.map.Error;

public class UserRequest {
    public String email;
    public String name;
    public String username;
    public String password;
    public int id;

    public List<Error> validate() {
        List<Error> errors = new ArrayList<>();
        if(email == null || email.isEmpty()) {
            errors.add(Error.MISSING_EMAIL);
        }
        if(name == null || name.isEmpty()) {
            errors.add(Error.MISSING_NAME);
        }
        if(username == null || username.isEmpty()) {
            errors.add(Error.MISSING_USERNAME);
        }
        if(password == null || password.isEmpty()) {
            errors.add(Error.MISSING_PASSWORD);
        }
        return errors;
    }
}

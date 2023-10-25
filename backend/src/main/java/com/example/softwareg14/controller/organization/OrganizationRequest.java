package com.example.softwareg14.controller.organization;

import com.example.softwareg14.controller.Error;

import java.util.ArrayList;
import java.util.List;

public class OrganizationRequest {
    public String name;
    public String address;
    public String phone;
    public String email;
    public String website;
    public String description;
    public String password;
    public int id;

    public List<Error> validate() {
        List<Error> errors = new ArrayList<>();
        if(name == null || name.isEmpty()) {
            errors.add(Error.MISSING_NAME);
        }
        if(address == null || address.isEmpty()) {
            errors.add(Error.MISSING_ADDRESS);
        }
        if(phone == null || phone.isEmpty()) {
            errors.add(Error.MISSING_PHONE);
        }
        if(email == null || email.isEmpty()) {
            errors.add(Error.MISSING_EMAIL);
        }
        if(website == null || website.isEmpty()) {
            errors.add(Error.MISSING_WEBSITE);
        }
        if(description == null || description.isEmpty()) {
            errors.add(Error.MISSING_DESCRIPTION);
        }
        if(password == null || password.isEmpty()) {
            errors.add(Error.MISSING_PASSWORD);
        }
        return errors;
    }
}

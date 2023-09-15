package com.example.softwareg14.map.organization;

public class OrganizationRequest {
    public String name;
    public String username;
    public String address;
    public String phone;
    public String email;
    public String website;
    public String description;
    public String password;

    public boolean validate() {
        if(name == null || name.isEmpty()) {
            return false;
        }
        if(username == null || username.isEmpty()) {
            return false;
        }
        if(address == null || address.isEmpty()) {
            return false;
        }
        if(phone == null || phone.isEmpty()) {
            return false;
        }
        if(email == null || email.isEmpty()) {
            return false;
        }
        if(website == null || website.isEmpty()) {
            return false;
        }
        if(description == null || description.isEmpty()) {
            return false;
        }
        if(password == null || password.isEmpty()) {
            return false;
        }
        return true;
    }
}

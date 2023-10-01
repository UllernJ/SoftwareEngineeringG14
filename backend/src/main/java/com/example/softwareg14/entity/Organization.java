package com.example.softwareg14.entity;

import lombok.Data;

@Data
public class Organization {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String description;
    private String password;

    public Organization(String name, String address, String phone, String email, String website, String description, String password) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.description = description;
        this.password = password;
    }

    public Organization() {
    }
}

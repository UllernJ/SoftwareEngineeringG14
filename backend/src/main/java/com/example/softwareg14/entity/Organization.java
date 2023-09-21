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
}

package com.example.softwareg14.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Organization {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String description;
    private String password;
}

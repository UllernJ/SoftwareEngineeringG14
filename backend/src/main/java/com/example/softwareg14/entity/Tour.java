package com.example.softwareg14.entity;

import lombok.Data;

@Data
public class Tour {
    private int id;
    private String name;
    private String description;
    private String duration;
    private String price;
    private String image;
    private String location;
    private String category;
    private Organization organization;
}

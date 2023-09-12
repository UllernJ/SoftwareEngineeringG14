package com.example.softwareg14.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Tour {
    private int id;
    private String name;
    private String description;
    private int durationHours;
    private int price;
    private String image;
    private String location;
    private String category;
    private Organization organization;
    private int attendingUsers;
    private int maxCapacity;
    private LocalDate date;

    public Tour(int id, String name, String description, int durationHours, int price, String image, String location, String category, Organization organization, int maxCapacity, LocalDate date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.durationHours = durationHours;
        this.price = price;
        this.image = image;
        this.location = location;
        this.category = category;
        this.organization = organization;
        this.maxCapacity = maxCapacity;
        if(date == null) {
            this.date = LocalDate.now();
        } else {
            this.date = date;
        }
    }
    public Tour() {

    }

    //must be rewritten somwhere else.
//    public void addAttendant(User user) {
//        if(usersAttending.size() >= maxCapacity && maxCapacity != 0) {
//            throw new RuntimeException("Tour is full");
//        } else if(usersAttending.contains(user)) {
//            throw new RuntimeException("User is already attending");
//        } else {
//            usersAttending.add(user);
//        }
//    }

}

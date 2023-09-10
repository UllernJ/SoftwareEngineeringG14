package com.example.softwareg14.entity;

import lombok.Data;

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
    private List<User> usersAttending = new ArrayList<>();
    private int maxCapacity;

    public void addAttendant(User user) {
        if(usersAttending.size() >= maxCapacity && maxCapacity != 0) {
            throw new RuntimeException("Tour is full");
        } else if(usersAttending.contains(user)) {
            throw new RuntimeException("User is already attending");
        } else {
            usersAttending.add(user);
        }
    }

}

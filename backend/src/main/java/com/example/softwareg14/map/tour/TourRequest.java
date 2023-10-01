package com.example.softwareg14.map.tour;


import com.example.softwareg14.entity.Organization;

import java.time.LocalDate;

public class TourRequest {
    public int id;
    public String name;
    public String description;
    public int durationHours;
    public int price;
    public String image;
    public String location;
    public String category;
    public Organization organization;
    public int attendingUsers;
    public int maxCapacity;
    public LocalDate date;
    public int tourId;
    public int userId;
}
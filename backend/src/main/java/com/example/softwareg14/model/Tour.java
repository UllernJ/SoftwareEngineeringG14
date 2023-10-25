package com.example.softwareg14.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}

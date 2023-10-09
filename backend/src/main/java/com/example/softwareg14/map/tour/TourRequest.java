package com.example.softwareg14.map.tour;


import com.example.softwareg14.entity.Organization;
import com.example.softwareg14.map.Error;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public List<Error> validate() {
        List<Error> errors = new ArrayList<>();
        if(name == null || name.isEmpty()) {
            errors.add(Error.MISSING_NAME);
        }
       if(description == null || description.isEmpty()) {
            errors.add(Error.MISSING_DESCRIPTION);
        }
       if(image == null || image.isEmpty()) {
            errors.add(Error.MISSING_IMAGE);
        }
       if(location == null || location.isEmpty()) {
            errors.add(Error.MISSING_LOCATION);
        }
       if(category == null || category.isEmpty()) {
            errors.add(Error.MISSING_CATEGORY);
        }
       if(organization == null) {
            errors.add(Error.MISSING_ORGANIZATION);
        }
        return errors;
    }


}
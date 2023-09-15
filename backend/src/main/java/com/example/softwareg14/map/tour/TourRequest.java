package com.example.softwareg14.map.tour;


public class TourRequest {

    public int tourId;
    public int userId;


    public boolean validate() {
        if(tourId == 0) {
            return false;
        }
        if(userId == 0) {
            return false;
        }
        return true;
    }



}

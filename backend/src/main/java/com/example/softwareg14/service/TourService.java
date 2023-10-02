package com.example.softwareg14.service;

import com.example.softwareg14.dao.TourDao;
import com.example.softwareg14.entity.Organization;
import com.example.softwareg14.entity.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TourService {
    private final TourDao tourDao;
    @Autowired
    public TourService(TourDao tourDao) { this.tourDao = tourDao; }

    public void createTour(String name, String description, int durationHours, int price, String image, String location, String category, Organization organization, int maxCapacity, LocalDate date) {
        Tour tour = new Tour(name, description, durationHours, price, image, location,category , organization, maxCapacity, date);
        tourDao.create(tour);
    }
    public void deleteTourById(int id) {
        tourDao.delete(id);
    }

    public List<Tour> getAllTours() {
        return tourDao.getAll();
    }

    public List<Tour> getToursByUserId(int id) {
        return tourDao.getToursByUserId(id);
    }

    public boolean isUserInTour(int userId, int tourId) {
        return tourDao.isUserInTour(userId, tourId);
    }

    public void addUserToTour(int userId, int tourId) {
        tourDao.addUserToTour(userId, tourId);
    }

    public void removeUserFromTour(int userId, int tourId) {
        tourDao.removeUserFromTour(userId, tourId);
    }

    public void updateTour(int id, String name, String description, int durationHours, int price, String image, String location, String category, Organization organization, int maxCapacity, LocalDate date) {
        Tour tour = new Tour(name, description, durationHours, price, image, location,category , organization, maxCapacity, date);
        tourDao.update(tour);
    }
}



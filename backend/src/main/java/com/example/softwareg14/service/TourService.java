package com.example.softwareg14.service;

import com.example.softwareg14.controller.Error;
import com.example.softwareg14.dao.TourDao;
import com.example.softwareg14.model.Tour;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TourService {
    @Autowired
    private TourDao tourDao;

    public TourService() {
    }

    public void createTour(Tour tour) {
        tourDao.create(tour);
    }

    public void deleteTourById(int id) {
        tourDao.delete(id);
    }

    public List<Tour> getToursbyOrganizationId(int id) {
        return tourDao.getToursByOrganizationId(id);
    }

    public Tour getTourById(int id) {
        return tourDao.getById(id);
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

    public void updateTour(Tour tour) {
        tourDao.update(tour);
    }

    public List<Error> isPersonEligibleForTour(int userId, int tourId) {
        List<Error> errors = new ArrayList<>();
        Tour tour = tourDao.getById(tourId);
        if (tour.getAttendingUsers() >= tour.getMaxCapacity()) {
            errors.add(Error.TOUR_IS_FULL);
        }
        if (tourDao.isUserInTour(userId, tourId)) {
            errors.add(Error.USER_ALREADY_IN_TOUR);
        }
        return errors;
    }
}



package com.example.softwareg14.map.tour;

import com.example.softwareg14.entity.Tour;
import com.example.softwareg14.map.Endpoint;
import com.example.softwareg14.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TourMap {
    private final TourService tourService;
    @Autowired
    public TourMap(TourService tourService) {
        this.tourService = tourService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(Endpoint.TOURS_ALL)
    public ResponseEntity<List<Tour>> getAllTours() {
        try {
            List<Tour> tours = tourService.getAllTours();
            return ResponseEntity.ok(tours);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(Endpoint.TOURS_BY_USER_ID)
    public ResponseEntity<List<Tour>> getToursByUserId(@PathVariable("id") int id) {
        try {
            List<Tour> tours = tourService.getToursByUserId(id);
            return ResponseEntity.ok(tours);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(Endpoint.ADD_USER_TO_TOUR)
    public ResponseEntity<String> addUserToTour(@RequestBody TourRequest tourRequest){
        try {
            int userId = tourRequest.userId;
            int tourId = tourRequest.tourId;
            int existingAttendeeCount = tourService.countAttendees(userId, tourId);
            if (existingAttendeeCount > 0) {
                return ResponseEntity.badRequest().body("User is already attending the tour.");
            } else {
                tourService.addUserToTour(userId, tourId);
                return ResponseEntity.ok("User added to the tour successfully.");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(Endpoint.CREATE_TOUR)
    public ResponseEntity<String> createTour(@RequestBody Tour tour) {
        try {
            String name = tour.getName();
            String description = tour.getDescription();
            int durationHours = tour.getDurationHours();
            int price = tour.getPrice();
            String image = tour.getImage();
            String location = tour.getLocation();
            int orgId = tour.getOrganization().getId();
            int maxCapacity = tour.getMaxCapacity();
            LocalDate date = tour.getDate();
            tourService.createTour(name, description, durationHours, price, image, location, orgId, maxCapacity, date);
            return ResponseEntity.ok("Tour created successfully.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


}







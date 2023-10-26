package com.example.softwareg14.controller.tour;

import com.example.softwareg14.controller.Endpoint;
import com.example.softwareg14.controller.Error;
import com.example.softwareg14.model.Tour;
import com.example.softwareg14.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TourController {
    private final TourService tourService;

    @Autowired
    public TourController(TourService tourService) {
        this.tourService = tourService;
    }


    @GetMapping(Endpoint.TOURS_ALL)
    public ResponseEntity<List<Tour>> getAllTours() {
        try {
            List<Tour> tours = tourService.getAllTours();
            return ResponseEntity.ok(tours);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping(Endpoint.TOURS_BY_USER_ID)
    public ResponseEntity<List<Tour>> getToursByUserId(@PathVariable("id") int id) {
        try {
            List<Tour> tours = tourService.getToursByUserId(id);
            return ResponseEntity.ok(tours);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(Endpoint.TOURS_BY_ORGANIZATION_ID)
    public ResponseEntity<List<Tour>> getToursByOrganizationId(@PathVariable("id") int id) {
        try {
            List<Tour> tours = tourService.getToursbyOrganizationId(id);
            return ResponseEntity.ok(tours);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(Endpoint.TOUR_BY_ID)
    public ResponseEntity<Tour> getTourById(@PathVariable("id") int id) {
        try {
            Tour tour = tourService.getTourById(id);
            return new ResponseEntity<>(tour, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(Endpoint.TOUR_UPDATE)
    public ResponseEntity<String> updateTour(@RequestBody TourRequest tourRequest, @PathVariable int id) {
        try {
            Tour tour = Tour.builder()
                    .id(id)
                    .name(tourRequest.name)
                    .description(tourRequest.description)
                    .durationHours(tourRequest.durationHours)
                    .price(tourRequest.price)
                    .image(tourRequest.image)
                    .maxCapacity(tourRequest.maxCapacity)
                    .date(tourRequest.date)
                    .location(tourRequest.location)
                    .category(tourRequest.category)
                    .build();

            tourService.updateTour(tour);

            return ResponseEntity.ok("Tour updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @DeleteMapping(Endpoint.DELETE_TOUR)
    public ResponseEntity<String> deleteTour(@PathVariable("id") int id) {
        try {
            tourService.deleteTourById(id);
            return ResponseEntity.ok("Tour deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @PostMapping(Endpoint.ADD_USER_TO_TOUR)
    public ResponseEntity<String> addUserToTour(@RequestBody TourRequest tourRequest) {
        try {
            int userId = tourRequest.userId;
            int tourId = tourRequest.tourId;
            List<Error> errors = tourService.isPersonEligibleForTour(userId, tourId);
            if (!errors.isEmpty()) {
                return ResponseEntity.badRequest().body(errors.toString());
            } else {
                tourService.addUserToTour(userId, tourId);
                return ResponseEntity.ok("User added to the tour successfully.");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @PostMapping(Endpoint.CREATE_TOUR)
    public ResponseEntity<String> createTour(@RequestBody TourRequest tourRequest) {
        try {
            Tour tour = Tour.builder()
                    .name(tourRequest.name)
                    .description(tourRequest.description)
                    .durationHours(tourRequest.durationHours)
                    .price(tourRequest.price)
                    .image(tourRequest.image)
                    .location(tourRequest.location)
                    .category(tourRequest.category)
                    .organization(tourRequest.organization)
                    .maxCapacity(tourRequest.maxCapacity)
                    .date(tourRequest.date)
                    .build();
            tourService.createTour(tour);
            return ResponseEntity.ok("Tour created successfully.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @PostMapping(Endpoint.REMOVE_USER_FROM_TOUR)
    public ResponseEntity<String> removeUserFromTour(@RequestBody TourRequest tourRequest) {
        try {
            if (tourService.isUserInTour(tourRequest.userId, tourRequest.tourId)) {
                tourService.removeUserFromTour(tourRequest.userId, tourRequest.tourId);
                return ResponseEntity.ok("User removed from the tour successfully.");
            } else {
                return ResponseEntity.badRequest().body("User is not attending the tour.");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}







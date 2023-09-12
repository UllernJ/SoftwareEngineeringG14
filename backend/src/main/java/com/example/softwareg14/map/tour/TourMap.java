package com.example.softwareg14.map.tour;

import com.example.softwareg14.entity.Tour;
import com.example.softwareg14.map.Endpoint;
import com.example.softwareg14.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

}

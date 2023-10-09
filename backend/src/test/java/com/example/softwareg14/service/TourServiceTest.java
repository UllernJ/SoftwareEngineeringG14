package com.example.softwareg14.service;

import com.example.softwareg14.config.AppConfigTest;
import com.example.softwareg14.entity.Tour;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TourServiceTest {
    private final TourService tourService = new AppConfigTest().tourService();
    private Tour tour;
    private Tour tourFromDb;

    @BeforeAll
    public void setup() {
        tour = Tour.builder()
                .name("test1")
                .description("test2")
                .durationHours(1)
                .price(100)
                .image("test3")
                .location("test4")
                .category("test5")
                .maxCapacity(10)
                .build();
        tourService.createTour(tour);
        tourFromDb = tourService.getToursById(tour.getId());
    }

    @Test
    public void testCreatingATourThatAlreadyExistShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            tourService.createTour(tour);
        });
    }

    @Test
    void createTourShouldBeEqualToTourFromDb() {
        assertEquals(tour.getName(), tourFromDb.getName());
        assertEquals(tour.getDescription(), tourFromDb.getDescription());
        assertEquals(tour.getDurationHours(), tourFromDb.getDurationHours());
        assertEquals(tour.getPrice(), tourFromDb.getPrice());
        assertEquals(tour.getImage(), tourFromDb.getImage());
        assertEquals(tour.getLocation(), tourFromDb.getLocation());
        assertEquals(tour.getCategory(), tourFromDb.getCategory());
        assertEquals(tour.getMaxCapacity(), tourFromDb.getMaxCapacity());
    }

    @Test
    void validateTour() throws NoSuchAlgorithmException {
    assertTrue(tourService.validateTour(tour.getId(), tour.getName()));
    }
}
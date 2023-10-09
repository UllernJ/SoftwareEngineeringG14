//package com.example.softwareg14.service;
//
//import com.example.softwareg14.config.AppConfigTest;
//import com.example.softwareg14.entity.Organization;
//import com.example.softwareg14.entity.Tour;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class TourServiceTest {
//    private final TourService tourService = new AppConfigTest().tourService();
//    private Tour tour;
//    private Tour tourFromDb;
//
//    @BeforeAll
//    public void setup() {
//        tour = Tour.builder()
//                .organization(
//                        Organization.builder()
//                                .id(999999)
//                                .build()
//                )
//                .name("test1")
//                .description("test2")
//                .durationHours(1)
//                .price(100)
//                .image("test3")
//                .location("test4")
//                .category("test5")
//                .maxCapacity(10)
//                .date(null)
//                .build();
//        tourService.createTour(tour);
//        tourFromDb = tourService.getToursbyOrganizationId(999999).get(0);
//    }
//
//    @Test
//    void createTourShouldBeEqualToTourFromDb() {
//        assertEquals(tour.getName(), tourFromDb.getName());
//        assertEquals(tour.getDescription(), tourFromDb.getDescription());
//        assertEquals(tour.getDurationHours(), tourFromDb.getDurationHours());
//        assertEquals(tour.getPrice(), tourFromDb.getPrice());
//        assertEquals(tour.getImage(), tourFromDb.getImage());
//        assertEquals(tour.getLocation(), tourFromDb.getLocation());
//        assertEquals(tour.getCategory(), tourFromDb.getCategory());
//        assertEquals(tour.getMaxCapacity(), tourFromDb.getMaxCapacity());
//        assertEquals(tour.getOrganization().getId(), tourFromDb.getOrganization().getId());
//    }
//
//    @AfterAll
//    public void tearDown() {
//        tourService.deleteTourById(tourFromDb.getId());
//    }
//}
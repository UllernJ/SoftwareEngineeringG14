package com.example.softwareg14.service;

import com.example.softwareg14.config.DatabaseConfig;
import com.example.softwareg14.config.DaoModule;
import com.example.softwareg14.config.ServiceModule;
import com.example.softwareg14.controller.Error;
import com.example.softwareg14.model.Organization;
import com.example.softwareg14.model.Tour;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {ServiceModule.class, DaoModule.class, DatabaseConfig.class})
class TourServiceTest {
    @Autowired
    private TourService tourService;
    @Autowired
    private OrganizationService organizationService;
    private Tour tour;
    private Organization organization;
    private Organization organizationFromDb;
    private int orgId;
    private Tour tourFromDb;

    @BeforeAll
    public void setup() throws NoSuchAlgorithmException {
        tour = Tour.builder()
                .name("test1")
                .description("test2")
                .durationHours(1)
                .price(100)
                .image("test3")
                .location("test4")
                .maxCapacity(10)
                .date(null)
                .build();
        organization = Organization.builder()
                .name("test")
                .description("test")
                .address("test")
                .phone("test")
                .email("test1234567890")
                .password("test")
                .website("test")
                .build();
        organizationService.createOrganization(organization);
        organizationFromDb = organizationService.getOrganizationByEmail(organization.getEmail(), "test");
        orgId = organizationFromDb.getId();
        tour.setOrganization(organizationFromDb);
        tourService.createTour(tour);
        tourFromDb = tourService.getToursbyOrganizationId(orgId).get(0);
    }

    @Test
    @Order(1)
    void testNameShouldBeEqualToTourFromDb() {
        assertEquals(tour.getName(), tourFromDb.getName());
    }

    @Test
    @Order(2)
    void testDescriptionShouldBeEqualToTourFromDb() {
        assertEquals(tour.getDescription(), tourFromDb.getDescription());
    }

    @Test
    @Order(3)
    void testDurationHoursShouldBeEqualToTourFromDb() {
        assertEquals(tour.getDurationHours(), tourFromDb.getDurationHours());
    }

    @Test
    @Order(4)
    void testPriceShouldBeEqualToTourFromDb() {
        assertEquals(tour.getPrice(), tourFromDb.getPrice());
    }

    @Test
    @Order(5)
    void testImageShouldBeEqualToTourFromDb() {
        assertEquals(tour.getImage(), tourFromDb.getImage());
    }

    @Test
    @Order(6)
    void testLocationShouldBeEqualToTourFromDb() {
        assertEquals(tour.getLocation(), tourFromDb.getLocation());
    }

    @Test
    @Order(7)
    void testMaxCapacityShouldBeEqualToTourFromDb() {
        assertEquals(tour.getMaxCapacity(), tourFromDb.getMaxCapacity());
    }

    @Test
    @Order(8)
    void testOrganizationIdShouldBeEqualToTourFromDb() {
        assertEquals(tour.getOrganization().getId(), tourFromDb.getOrganization().getId());
    }

    @Test
    @Order(9)
    void editTourNameShouldBeEqualToTourFromDb() {
        tourFromDb.setName("test5");
        tourService.updateTour(tourFromDb);
        Tour tourFromDbAfterUpdate = tourService.getToursbyOrganizationId(orgId).get(0);
        assertEquals(tourFromDb.getName(), tourFromDbAfterUpdate.getName());
    }

    @Test
    @Order(10)
    void editTourDescriptionShouldBeEqualToTourFromDb() {
        tourFromDb.setDescription("test6");
        tourService.updateTour(tourFromDb);
        Tour tourFromDbAfterUpdate = tourService.getToursbyOrganizationId(orgId).get(0);
        assertEquals(tourFromDb.getDescription(), tourFromDbAfterUpdate.getDescription());
    }

    @Test
    @Order(11)
    void editTourDurationHoursShouldBeEqualToTourFromDb() {
        tourFromDb.setDurationHours(2);
        tourService.updateTour(tourFromDb);
        Tour tourFromDbAfterUpdate = tourService.getToursbyOrganizationId(orgId).get(0);
        assertEquals(tourFromDb.getDurationHours(), tourFromDbAfterUpdate.getDurationHours());
    }

    @Test
    @Order(12)
    void editTourPriceShouldBeEqualToTourFromDb() {
        tourFromDb.setPrice(200);
        tourService.updateTour(tourFromDb);
        Tour tourFromDbAfterUpdate = tourService.getToursbyOrganizationId(orgId).get(0);
        assertEquals(tourFromDb.getPrice(), tourFromDbAfterUpdate.getPrice());
    }

    @Test
    @Order(13)
    void editTourImageShouldBeEqualToTourFromDb() {
        tourFromDb.setImage("test7");
        tourService.updateTour(tourFromDb);
        Tour tourFromDbAfterUpdate = tourService.getToursbyOrganizationId(orgId).get(0);
        assertEquals(tourFromDb.getImage(), tourFromDbAfterUpdate.getImage());
    }

    @Test
    @Order(14)
    void editTourLocationShouldBeEqualToTourFromDb() {
        tourFromDb.setLocation("test8");
        tourService.updateTour(tourFromDb);
        Tour tourFromDbAfterUpdate = tourService.getToursbyOrganizationId(orgId).get(0);
        assertEquals(tourFromDb.getLocation(), tourFromDbAfterUpdate.getLocation());
    }

    @Test
    @Order(15)
    void editTourMaxCapacityShouldBeEqualToTourFromDb() {
        tourFromDb.setMaxCapacity(5);
        tourService.updateTour(tourFromDb);
        Tour tourFromDbAfterUpdate = tourService.getToursbyOrganizationId(orgId).get(0);
        assertEquals(tourFromDb.getMaxCapacity(), tourFromDbAfterUpdate.getMaxCapacity());
    }

    @Test
    @Order(16)
    void editTourDateShouldBeEqualToTourFromDb() {
        tourFromDb.setDate(null);
        tourService.updateTour(tourFromDb);
        Tour tourFromDbAfterUpdate = tourService.getToursbyOrganizationId(orgId).get(0);
        assertEquals(tourFromDb.getDate(), tourFromDbAfterUpdate.getDate());
    }

    @Test
    @Order(17)
    void addAttendeeShouldBeTrue() {
        tourService.addUserToTour(orgId, tourFromDb.getId());
        assertTrue(tourService.isUserInTour(orgId, tourFromDb.getId()));
    }

    @Test
    @Order(18)
    void removeAttendeeShouldBeFalse() {
        tourService.removeUserFromTour(orgId, tourFromDb.getId());
        assertFalse(tourService.isUserInTour(orgId, tourFromDb.getId()));
    }

    @Test
    @Order(19)
    void tourShouldHave3Attendees() {
        tourService.addUserToTour(1, tourFromDb.getId());
        tourService.addUserToTour(2, tourFromDb.getId());
        tourService.addUserToTour(3, tourFromDb.getId());
        tourFromDb = tourService.getToursbyOrganizationId(orgId).get(0);
        assertEquals(3, tourFromDb.getAttendingUsers());
    }

    @Test
    @Order(20)
    void addingSameAttendeeResultInError() {
        List<Error> errors = tourService.isPersonEligibleForTour(1, tourFromDb.getId());
        assertEquals(1, errors.size());
        assertEquals(Error.USER_ALREADY_IN_TOUR, errors.get(0));
    }

    @Test
    @Order(21)
    void addingAttendeesToFullTourShouldResultInError() {
        tourService.addUserToTour(4, tourFromDb.getId());
        tourService.addUserToTour(5, tourFromDb.getId());
        List<Error> errors = tourService.isPersonEligibleForTour(6, tourFromDb.getId());
        assertEquals(1, errors.size());
        assertEquals(Error.TOUR_IS_FULL, errors.get(0));
    }

    @Test
    @Order(22)
    void getAllShouldReturnAllTours() {
        List<Tour> tours = tourService.getAllTours();
        assertTrue(tours.size() > 0);
    }

    @Test
    @Order(23)
    void ShouldReturnAllToursByOrganizationId() {
        List<Tour> tours = tourService.getToursbyOrganizationId(orgId);
        assertTrue(tours.size() > 0);
    }

    @Test
    @Order(24)
    void ShouldReturnNoToursByOrganizationId() {
        List<Tour> tours = tourService.getToursbyOrganizationId(696969);
        assertTrue(tours.size() == 0);
    }

    @Test
    @Order(25)
    void ShouldReturnAllToursByUserId() {
        List<Tour> tours = tourService.getToursByUserId(1);
        assertTrue(tours.size() > 0);
    }

    @Test
    @Order(26)
    void shouldReturnTourById() {
        Tour tour = tourService.getTourById(tourFromDb.getId());
        assertEquals(tourFromDb.getId(), tour.getId());
    }

    @AfterAll
    public void tearDown() {
        tourService.deleteTourById(tourFromDb.getId());
        tourService.removeUserFromTour(1, tourFromDb.getId());
        tourService.removeUserFromTour(2, tourFromDb.getId());
        tourService.removeUserFromTour(3, tourFromDb.getId());
        tourService.removeUserFromTour(4, tourFromDb.getId());
        tourService.removeUserFromTour(5, tourFromDb.getId());
        organizationService.deleteOrganizationById(orgId);
    }
}
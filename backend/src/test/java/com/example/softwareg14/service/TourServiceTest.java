package com.example.softwareg14.service;

import com.example.softwareg14.config.AppConfigTest;
import com.example.softwareg14.entity.Organization;
import com.example.softwareg14.entity.Tour;
import com.example.softwareg14.map.Error;
import org.junit.jupiter.api.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TourServiceTest {
    private final TourService tourService = new AppConfigTest().tourService();
    private final OrganizationService organizationService = new AppConfigTest().organizationService();
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
        organizationFromDb = organizationService.getOrganizationByEmail("test1234567890");
        orgId = organizationFromDb.getId();
        tour.setOrganization(organizationFromDb);
        tourService.createTour(tour);
        tourFromDb = tourService.getToursbyOrganizationId(orgId).get(0);
    }

    @Test
    @Order(1)
    void createTourShouldBeEqualToTourFromDb() {
        assertEquals(tour.getName(), tourFromDb.getName());
        assertEquals(tour.getDescription(), tourFromDb.getDescription());
        assertEquals(tour.getDurationHours(), tourFromDb.getDurationHours());
        assertEquals(tour.getPrice(), tourFromDb.getPrice());
        assertEquals(tour.getImage(), tourFromDb.getImage());
        assertEquals(tour.getLocation(), tourFromDb.getLocation());
        assertEquals(tour.getMaxCapacity(), tourFromDb.getMaxCapacity());
        assertEquals(tour.getOrganization().getId(), tourFromDb.getOrganization().getId());
    }

    @Test
    @Order(2)
    void editTourShouldBeEqualToTourFromDb() {
        tourFromDb.setName("test5");
        tourFromDb.setDescription("test6");
        tourFromDb.setDurationHours(2);
        tourFromDb.setPrice(200);
        tourFromDb.setImage("test7");
        tourFromDb.setLocation("test8");
        tourFromDb.setMaxCapacity(5);
        tourFromDb.setDate(null);
        tourService.updateTour(tourFromDb);
        Tour tourFromDbAfterUpdate = tourService.getToursbyOrganizationId(orgId).get(0);
        assertEquals(tourFromDb.getName(), tourFromDbAfterUpdate.getName());
        assertEquals(tourFromDb.getDescription(), tourFromDbAfterUpdate.getDescription());
        assertEquals(tourFromDb.getDurationHours(), tourFromDbAfterUpdate.getDurationHours());
        assertEquals(tourFromDb.getPrice(), tourFromDbAfterUpdate.getPrice());
        assertEquals(tourFromDb.getImage(), tourFromDbAfterUpdate.getImage());
        assertEquals(tourFromDb.getLocation(), tourFromDbAfterUpdate.getLocation());
        assertEquals(tourFromDb.getMaxCapacity(), tourFromDbAfterUpdate.getMaxCapacity());
        assertEquals(tourFromDb.getOrganization().getId(), tourFromDbAfterUpdate.getOrganization().getId());
    }

    @Test
    @Order(3)
    void addAttendeeShouldBeTrue() {
        tourService.addUserToTour(orgId, tourFromDb.getId());
        assertTrue(tourService.isUserInTour(orgId, tourFromDb.getId()));
    }

    @Test
    @Order(4)
    void removeAttendeeShouldBeFalse() {
        tourService.removeUserFromTour(orgId, tourFromDb.getId());
        assertFalse(tourService.isUserInTour(orgId, tourFromDb.getId()));
    }

    @Test
    @Order(5)
    void tourShouldHave3Attendees() {
        tourService.addUserToTour(1, tourFromDb.getId());
        tourService.addUserToTour(2, tourFromDb.getId());
        tourService.addUserToTour(3, tourFromDb.getId());
        tourFromDb = tourService.getToursbyOrganizationId(orgId).get(0);
        assertEquals(3, tourFromDb.getAttendingUsers());
    }

    @Test
    @Order(6)
    void addingSameAttendeeResultInError() {
        List<Error> errors = tourService.isPersonEligibleForTour(1, tourFromDb.getId());
        assertEquals(1, errors.size());
        assertEquals(Error.USER_ALREADY_IN_TOUR, errors.get(0));
    }

    @Test
    @Order(7)
    void addingAttendeesToFullTourShouldResultInError() {
        tourService.addUserToTour(4, tourFromDb.getId());
        tourService.addUserToTour(5, tourFromDb.getId());
        List<Error> errors = tourService.isPersonEligibleForTour(6, tourFromDb.getId());
        assertEquals(1, errors.size());
        assertEquals(Error.TOUR_IS_FULL, errors.get(0));
    }

    @Test
    @Order(8)
    void getAllShouldReturnAllTours() {
        List<Tour> tours = tourService.getAllTours();
        assertTrue(tours.size() > 0);
    }

    @Test
    @Order(9)
    void ShouldReturnAllToursByOrganizationId() {
        List<Tour> tours = tourService.getToursbyOrganizationId(orgId);
        assertTrue(tours.size() > 0);
    }

    @Test
    @Order(10)
    void ShouldReturnAllToursByUserId() {
        List<Tour> tours = tourService.getToursByUserId(1);
        assertTrue(tours.size() > 0);
    }

    @Test
    @Order(11)
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
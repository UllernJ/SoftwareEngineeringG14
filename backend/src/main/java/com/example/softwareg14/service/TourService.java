package com.example.softwareg14.service;

import com.example.softwareg14.entity.Organization;
import com.example.softwareg14.entity.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TourService {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public TourService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void createTour(String name, String description, int durationHours, int price, String image, String location, int orgId, int maxCapacity, LocalDate date) {
        jdbcTemplate.update("INSERT INTO tour (name, description, durationHours, price, image, location, orgId, maxCapacity, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", name, description, durationHours, price, image, location, orgId, maxCapacity, date.toString());
    }
    public void deleteTourById(int id) {
        jdbcTemplate.update("DELETE FROM tour WHERE id = ?", id);
    }

    public List<Tour> getAllTours() {
        String query = "SELECT " +
                "tour.id AS id, " +
                "tour.name AS tour_name, " +
                "tour.description AS tour_description, " +
                "tour.durationHours AS tour_durationHours, " +
                "tour.price AS tour_price, " +
                "tour.image AS tour_image, " +
                "tour.location AS tour_location, " +
                "tour.maxCapacity AS tour_maxCapacity, " +
                "tour.date AS tour_date, " +
                "organization.id AS org_id, " +
                "organization.name AS org_name, " +
                "organization.description AS org_description, " +
                "organization.address AS org_address, " +
                "organization.website AS org_website, " +
                "organization.phone AS org_phone, " +
                "organization.email AS org_email, " +
                "count(userHasTour.userId) as attendingUsers " +
                "FROM tour " +
                "INNER JOIN organization ON tour.orgId = organization.id " +
                "INNER JOIN userHasTour on userHasTour.tourId = tour.id " +
                "GROUP BY tour.id";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Tour tour = new Tour();
            tour.setId(rs.getInt("id"));
            tour.setName(rs.getString("tour_name"));
            tour.setDescription(rs.getString("tour_description"));
            tour.setDurationHours(rs.getInt("tour_durationHours"));
            tour.setPrice(rs.getInt("tour_price"));
            tour.setImage(rs.getString("tour_image"));
            tour.setLocation(rs.getString("tour_location"));
            tour.setMaxCapacity(rs.getInt("tour_maxCapacity"));
            tour.setAttendingUsers(rs.getInt("attendingUsers"));
            tour.setDate(null);

            Organization organization = new Organization();
            organization.setId(rs.getInt("org_id"));
            organization.setName(rs.getString("org_name"));
            organization.setDescription(rs.getString("org_description"));
            organization.setAddress(rs.getString("org_address"));
            organization.setWebsite(rs.getString("org_website"));
            organization.setPhone(rs.getString("org_phone"));
            organization.setEmail(rs.getString("org_email"));

            tour.setOrganization(organization);
            return tour;
        });

    }

    public List<Tour> getToursByUserId(int id) {
        String query = "SELECT " +
                "tour.id AS id, " +
                "tour.name AS tour_name, " +
                "tour.description AS tour_description, " +
                "tour.durationHours AS tour_durationHours, " +
                "tour.price AS tour_price, " +
                "tour.image AS tour_image, " +
                "tour.location AS tour_location, " +
                "tour.maxCapacity AS tour_maxCapacity, " +
                "tour.date AS tour_date, " +
                "organization.id AS org_id, " +
                "organization.name AS org_name, " +
                "organization.description AS org_description, " +
                "organization.address AS org_address, " +
                "organization.website AS org_website, " +
                "organization.phone AS org_phone, " +
                "organization.email AS org_email, " +
                "count(userHasTour.userId) as attendingUsers " +
                "FROM tour " +
                "INNER JOIN organization ON tour.orgId = organization.id " +
                "INNER JOIN userHasTour on userHasTour.tourId = tour.id " +
                "WHERE userHasTour.userId = ? " +
                "GROUP BY tour.id";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Tour tour = new Tour();
            tour.setId(rs.getInt("id"));
            tour.setName(rs.getString("tour_name"));
            tour.setDescription(rs.getString("tour_description"));
            tour.setDurationHours(rs.getInt("tour_durationHours"));
            tour.setPrice(rs.getInt("tour_price"));
            tour.setImage(rs.getString("tour_image"));
            tour.setLocation(rs.getString("tour_location"));
            tour.setMaxCapacity(rs.getInt("tour_maxCapacity"));
            tour.setAttendingUsers(rs.getInt("attendingUsers"));
            tour.setDate(null);

            Organization organization = new Organization();
            organization.setId(rs.getInt("org_id"));
            organization.setName(rs.getString("org_name"));
            organization.setDescription(rs.getString("org_description"));
            organization.setAddress(rs.getString("org_address"));
            organization.setWebsite(rs.getString("org_website"));
            organization.setPhone(rs.getString("org_phone"));
            organization.setEmail(rs.getString("org_email"));

            tour.setOrganization(organization);
            return tour;
        }, id);

    }
    public int countAttendees(int userId, int tourId) {
        String query = "SELECT COUNT(*) FROM userHasTour WHERE userId = ? AND tourId = ?";
        return jdbcTemplate.queryForObject(query, Integer.class, userId, tourId);
    }
    public void addUserToTour(int userId, int tourId) {
        jdbcTemplate.update("INSERT INTO userHasTour (userId, tourId) VALUES (?, ?)", userId, tourId);

        jdbcTemplate.update("UPDATE tour SET attendee = attendee + 1 WHERE tourId = ?", tourId);
    }

    public void removeUserFromTour(int userId, int tourId) {
        jdbcTemplate.update("DELETE FROM userHasTour WHERE userId = ? AND tourId = ?", userId, tourId);

        jdbcTemplate.update("UPDATE tour SET attendee = attendee - 1 WHERE tourId = ?", tourId);
    }





}

package com.example.softwareg14.dao;
import com.example.softwareg14.entity.Organization;
import com.example.softwareg14.entity.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
@Component
public class TourDao implements Dao<Tour> {

    private List<Tour> tours;
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public TourDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tour getById(int id) {
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
                "WHERE tour.id = ? " +
                "GROUP BY tour.id";
        return jdbcTemplate.queryForObject(query, (rs, rowNum) -> {
            Tour tour = new Tour();
            tour.setId(rs.getInt("id"));
            tour.setName(rs.getString("tour_name"));
            tour.setDescription(rs.getString("tour_description"));
            tour.setDurationHours(rs.getInt("tour_durationHours"));
            tour.setPrice(rs.getInt("tour_price"));
            tour.setImage(rs.getString("tour_image"));
            tour.setLocation(rs.getString("tour_location"));
            tour.setMaxCapacity(rs.getInt("tour_maxCapacity"));
            tour.setDate(rs.getDate("tour_date").toLocalDate());
            Organization organization = new Organization();
            organization.setId(rs.getInt("org_id"));
            organization.setName(rs.getString("org_name"));
            organization.setDescription(rs.getString("org_description"));
            organization.setAddress(rs.getString("org_address"));
            organization.setWebsite(rs.getString("org_website"));
            organization.setPhone(rs.getString("org_phone"));
            organization.setEmail(rs.getString("org_email"));
            tour.setOrganization(organization);
            tour.setAttendingUsers(rs.getInt("attendingUsers"));
            return tour;
        }, id);
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

    @Override
    public void create(Tour tour) {
        String query = "INSERT INTO tour (name, description, durationHours, price, image, location, maxCapacity, date, orgId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                query,
                tour.getName(),
                tour.getDescription(),
                tour.getDurationHours(),
                tour.getPrice(),
                tour.getImage(),
                tour.getLocation(),
                tour.getMaxCapacity(),
                tour.getDate(),
                tour.getOrganization()
        );
    }

    @Override
    public void update(Tour tour) {
        jdbcTemplate.update("UPDATE tour SET name = ?, description = ?, durationHours = ?, price = ?, image = ?, location = ?, maxCapacity = ?, date = ?, orgId = ? WHERE id = ?",
                tour.getName(), tour.getDescription(), tour.getDurationHours(), tour.getPrice(), tour.getImage(), tour.getLocation(), tour.getMaxCapacity(), tour.getDate(), tour.getOrganization(), tour.getId());
    }

    @Override
    public void delete(int id)  {
        jdbcTemplate.update("DELETE FROM tour WHERE id = ?", id);
    }

    @Override
    public List<Tour> getAll() {
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
                "LEFT JOIN userHasTour on userHasTour.tourId = tour.id " +
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

    public boolean isUserInTour(int userId, int tourId) {
        String query = "SELECT COUNT(*) FROM userHasTour WHERE userId = ? AND tourId = ?";
        int count = jdbcTemplate.queryForObject(query, Integer.class, userId, tourId);
        return count > 0;
    }

    public void addUserToTour(int userId, int tourId) {
        jdbcTemplate.update("INSERT INTO userHasTour (userId, tourId) VALUES (?, ?)", userId, tourId);
    }

    public void removeUserFromTour(int userId, int tourId) {
        jdbcTemplate.update("DELETE FROM userHasTour WHERE userId = ? AND tourId = ?", userId, tourId);
    }
}

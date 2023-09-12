package com.example.softwareg14.service;

import com.example.softwareg14.entity.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrganizationService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createOrganization(String name, String description, String address, String website, String phone, String email, String password) {
        jdbcTemplate.update("INSERT INTO organization (name, description, address, website, phone, email, password) VALUES (?, ?, ?, ?, ?, ?, ?)", name, description, address, website, phone, email, password);
    }

    public void deleteOrganizationById(int id) {
        jdbcTemplate.update("DELETE FROM organization WHERE id = ?", id);
    }

    public void updateOrganizationById(int id, String name, String description, String address, String phone, String email) {
        jdbcTemplate.update("UPDATE organization SET name = ?, description = ?, address = ?, phone = ?, email = ? WHERE id = ?", name, description, address, phone, email, id);
    }

    public List<Organization> getAllOrganizations() {
        return jdbcTemplate.query("SELECT * FROM organization", (rs, rowNum) -> {
            Organization organization = new Organization();
            organization.setId(rs.getInt("id"));
            organization.setName(rs.getString("name"));
            organization.setDescription(rs.getString("description"));
            organization.setAddress(rs.getString("address"));
            organization.setWebsite(rs.getString("website"));
            organization.setPhone(rs.getString("phone"));
            organization.setEmail(rs.getString("email"));
            return organization;
        });
    }

}

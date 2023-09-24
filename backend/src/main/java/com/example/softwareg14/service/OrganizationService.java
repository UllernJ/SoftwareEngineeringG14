package com.example.softwareg14.service;

import com.example.softwareg14.entity.Organization;
import com.example.softwareg14.entity.User;
import com.example.softwareg14.map.organization.OrganizationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class OrganizationService {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public OrganizationService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createOrganization(OrganizationRequest organizationRequest) throws NoSuchAlgorithmException {
        organizationRequest.password = HashService.hashPassword(organizationRequest.password);
        jdbcTemplate.update("INSERT INTO organization (name, description, address, website, phone, email, password) VALUES (?, ?, ?, ?, ?, ?, ?)",
                organizationRequest.name, organizationRequest.description, organizationRequest.address, organizationRequest.website, organizationRequest.phone, organizationRequest.email, organizationRequest.password);
    }

    public void deleteOrganizationById(int id) {
        jdbcTemplate.update("DELETE FROM organization WHERE id = ?", id);
    }

    public void updateOrganizationById(int id, String name, String description, String address, String phone, String email) {
        jdbcTemplate.update("UPDATE organization SET name = ?, description = ?, address = ?, phone = ?, email = ? WHERE id = ?", name, description, address, phone, email, id);
    }

    public boolean validateOrganization(String email, String password) throws NoSuchAlgorithmException {
        password = HashService.hashPassword(password);
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM organization WHERE email = ? AND password = ?", new Object[]{email, password}, Integer.class) > 0;
    }

    public Organization getOrganizationByEmail(String email) {
        return jdbcTemplate.queryForObject("SELECT * FROM organization WHERE email = ?", new Object[]{email}, (rs, rowNum) -> {
            Organization organization = new Organization();
            organization.setId(rs.getInt("id"));
            organization.setName(rs.getString("name"));
            organization.setEmail(rs.getString("email"));
            return organization;
        });
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

    public Organization getOrganizationById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM organization WHERE id = ?", (rs, rowNum) -> {
            Organization organization = new Organization();
            organization.setId(rs.getInt("id"));
            organization.setName(rs.getString("name"));
            organization.setDescription(rs.getString("description"));
            organization.setAddress(rs.getString("address"));
            organization.setWebsite(rs.getString("website"));
            organization.setPhone(rs.getString("phone"));
            organization.setEmail(rs.getString("email"));
            return organization;
        }, id);
    }

}

package com.example.softwareg14.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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

}

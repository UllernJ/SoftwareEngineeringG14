package com.example.softwareg14.dao;

import com.example.softwareg14.model.Organization;
import com.example.softwareg14.service.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class OrganizationDao implements Dao<Organization> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final List<Organization> organizations = new ArrayList<>();

    @Override
    public Organization getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM organization WHERE id = ?", (rs, rowNum) -> {
            Organization organization = Organization.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .address(rs.getString("address"))
                    .website(rs.getString("website"))
                    .phone(rs.getString("phone"))
                    .email(rs.getString("email"))
                    .build();
            return organization;
        }, id);
    }

    @Override
    public void create(Organization organization) {
        jdbcTemplate.update("INSERT INTO organization (name, description, address, website, phone, email, password) VALUES (?, ?, ?, ?, ?, ?, ?)",
                organization.getName(), organization.getDescription(), organization.getAddress(), organization.getWebsite(), organization.getPhone(), organization.getEmail(), organization.getPassword());
    }

    @Override
    public void update(Organization organization) {
        jdbcTemplate.update("UPDATE organization SET name = ?, description = ?, address = ?, phone = ?, email = ? WHERE id = ?",
                organization.getName(), organization.getDescription(), organization.getAddress(), organization.getPhone(), organization.getEmail(), organization.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM organization WHERE id = ?", id);
    }

    @Override
    public List<Organization> getAll() {
        jdbcTemplate.query("SELECT * FROM organization", (rs, rowNum) -> {
            Organization organization = Organization.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .address(rs.getString("address"))
                    .website(rs.getString("website"))
                    .phone(rs.getString("phone"))
                    .email(rs.getString("email"))
                    .build();
            this.organizations.add(organization);
            return organization;
        });
        return organizations;
    }

    public Organization getOrganizationByEmail(String email) {
        return jdbcTemplate.queryForObject("SELECT * FROM organization WHERE email = ?", new Object[]{email}, (rs, rowNum) -> {
            Organization organization = Organization.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .address(rs.getString("address"))
                    .website(rs.getString("website"))
                    .phone(rs.getString("phone"))
                    .email(rs.getString("email"))
                    .build();
            return organization;
        });
    }

    public boolean validateOrganization(String email, String password) throws NoSuchAlgorithmException {
        String hashedPassword = HashService.hashPassword(password);
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM organization WHERE email = ? AND password = ?", new Object[]{email, hashedPassword}, Integer.class) > 0;
    }

    public boolean organizationExists(String email) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM organization WHERE email = ?", new Object[]{email}, Integer.class) > 0;
    }
}

package com.example.softwareg14.dao;

import com.example.softwareg14.entity.Organization;
import com.example.softwareg14.entity.Tour;
import com.example.softwareg14.map.organization.OrganizationRequest;
import com.example.softwareg14.service.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
@Service
public class OrganizationDao implements Dao<Organization> {

    private List<Organization> organization;
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public OrganizationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Organization getById(int id) {
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

    public Organization getOrganizationByEmail(String email) {
        return jdbcTemplate.queryForObject("SELECT * FROM organization WHERE email = ?", new Object[]{email}, (rs, rowNum) -> {
            Organization organization = new Organization();
            organization.setId(rs.getInt("id"));
            organization.setName(rs.getString("name"));
            organization.setEmail(rs.getString("email"));
            return organization;
        });
    }

    public boolean validateOrganization(String email, String password) throws NoSuchAlgorithmException {
        password = HashService.hashPassword(password);
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM organization WHERE email = ? AND password = ?", new Object[]{email, password}, Integer.class) > 0;
    }

    public boolean organizationExists(String email) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM organization WHERE email = ?", new Object[]{email}, Integer.class) > 0;
    }
}

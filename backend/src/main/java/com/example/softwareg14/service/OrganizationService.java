package com.example.softwareg14.service;

import com.example.softwareg14.dao.OrganizationDao;
import com.example.softwareg14.entity.Organization;
import com.example.softwareg14.entity.User;
import com.example.softwareg14.map.organization.OrganizationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class OrganizationService {
    private final OrganizationDao organizationDao;
    @Autowired
    public OrganizationService(OrganizationDao organizationDao) { this.organizationDao = organizationDao; }

    public void createOrganization(Organization organization) throws NoSuchAlgorithmException {
        if (organizationExists(organization.getEmail())) {
            throw new IllegalArgumentException("Organization already exists");
        }
        organization.setPassword(HashService.hashPassword(organization.getPassword()));
        organizationDao.create(organization);
    }

    public void deleteOrganizationById(int id) {
        organizationDao.delete(id);
    }

    public void updateOrganization(Organization organization) {
        organizationDao.update(organization);
    }

    public boolean validateOrganization(String email, String password) throws NoSuchAlgorithmException {
        return organizationDao.validateOrganization(email, password);
    }

    public Organization getOrganizationByEmail(String email) {
        return organizationDao.getOrganizationByEmail(email);
    }

    public List<Organization> getAllOrganizations() {
        return organizationDao.getAll();
    }

    public Organization getOrganizationById(int id) {
        return organizationDao.getById(id);
    }



    public boolean organizationExists(String email) {
        return organizationDao.organizationExists(email);
    }
}

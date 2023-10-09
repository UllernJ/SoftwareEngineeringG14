package com.example.softwareg14.service;

import com.example.softwareg14.dao.OrganizationDao;
import com.example.softwareg14.entity.Organization;
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
        if(getOrganizationById(id) != null) {
            organizationDao.delete(id);
        }
    }

    public void updateOrganization(Organization organization) {
        if(getOrganizationById(organization.getId()) != null) {
            organizationDao.update(organization);
        }
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
        Organization org = organizationDao.getById(id);
        if(org == null) {
            throw new IllegalArgumentException("Organization does not exist");
        }
        return org;
    }

    public boolean organizationExists(String email) {
        return organizationDao.organizationExists(email);
    }
}

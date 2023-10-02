package com.example.softwareg14.service;

import com.example.softwareg14.config.AppConfigTest;
import com.example.softwareg14.entity.Organization;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrganizationServiceTest {
    private final OrganizationService organizationService = new AppConfigTest().organizationService();
    private Organization organization;
    private Organization organizationFromDb;

    @BeforeAll
    public void setUp() throws NoSuchAlgorithmException {
        organization = Organization.builder()
                .name("test1")
                .address("test2")
                .phone("test3")
                .email("test4")
                .website("test5")
                .description("test6")
                .password("test7")
                .build();
        organizationService.createOrganization(organization);
        organization.setPassword("test7");
        organizationFromDb = organizationService.getOrganizationByEmail(organization.getEmail());
    }

    @Test
    public void testCreatingAOrganizationThatAlreadyExistShouldThrowException() throws NoSuchAlgorithmException {
        assertThrows(IllegalArgumentException.class, () -> {
            organizationService.createOrganization(organization);
        });
    }

    @Test
    void createdOrganizationShouldBeEqualToOrganizationFromDb() {
        assertEquals(organization.getEmail(), organizationFromDb.getEmail());
        assertEquals(organization.getName(), organizationFromDb.getName());
        assertEquals(organization.getAddress(), organizationFromDb.getAddress());
        assertEquals(organization.getPhone(), organizationFromDb.getPhone());
        assertEquals(organization.getWebsite(), organizationFromDb.getWebsite());
        assertEquals(organization.getDescription(), organizationFromDb.getDescription());
    }

    @Test
    void validateOrganization() throws NoSuchAlgorithmException {
        assertTrue(organizationService.validateOrganization(organization.getEmail(), organization.getPassword()));
    }
    @Test
    void updateOrganization() {
        organizationFromDb.setName("test8");
        organizationService.updateOrganization(organizationFromDb);
        organizationFromDb = organizationService.getOrganizationByEmail(organization.getEmail());
        assertEquals("test8", organizationFromDb.getName());
    }

    @Test
    void getAllOrganizations() {
        List<Organization> organizations = organizationService.getAllOrganizations();
        assertTrue(organizations.size() > 0);
        assertTrue(organizations.contains(organizationFromDb));
    }

    @Test
    void getOrganizationById() {
        Organization organizationById = organizationService.getOrganizationById(organizationFromDb.getId());
        assertEquals(organizationFromDb, organizationById);
    }

    @Test
    void organizationExists() {
        assertTrue(organizationService.organizationExists(organizationFromDb.getEmail()));
    }

    @AfterAll
    public void tearDown() {
        organizationService.deleteOrganizationById(organizationFromDb.getId());
        assertFalse(organizationService.organizationExists(organizationFromDb.getEmail()));
    }
}
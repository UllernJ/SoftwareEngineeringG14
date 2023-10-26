package com.example.softwareg14.service;

import com.example.softwareg14.config.DatabaseConfig;
import com.example.softwareg14.config.DaoModule;
import com.example.softwareg14.config.ServiceModule;
import com.example.softwareg14.model.Organization;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = {ServiceModule.class, DaoModule.class, DatabaseConfig.class})
@TestConfiguration
class OrganizationServiceTest {
    @Autowired
    private OrganizationService organizationService;
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
    void updateOrganizationShouldUpdateTheCorrectValuesAndMatch() {
        organizationFromDb.setName("test8");
        organizationService.updateOrganization(organizationFromDb);
        organizationFromDb = organizationService.getOrganizationByEmail(organization.getEmail());
        assertEquals("test8", organizationFromDb.getName());
    }

    @Test
    void getAllOrganizationsShouldBeAListLargerThanZero() {
        List<Organization> organizations = organizationService.getAllOrganizations();
        assertTrue(organizations.size() > 0);
        assertTrue(organizations.contains(organizationFromDb));
    }

    @Test
    void registeredOrganizationShouldBeFetchedFromDB() {
        Organization organizationById = organizationService.getOrganizationById(organizationFromDb.getId());
        assertEquals(organizationFromDb, organizationById);
    }

    @Test
    void registeredOrganizationShouldExist() {
        assertTrue(organizationService.organizationExists(organizationFromDb.getEmail()));
    }

    @Test
    public void testUpdateNonExistingOrganization() {
        Organization nonExistingOrganization = Organization.builder()
                .id(696423439)
                .name("nonExisting")
                .address("NonExisting")
                .build();

        assertThrows(RuntimeException.class, () -> {
            organizationService.updateOrganization(nonExistingOrganization);
        });
    }

    @Test
    public void testGetOrganizationByNonExistentId() {
        assertThrows(RuntimeException.class, () -> {
            organizationService.getOrganizationById(4121241);
        });
    }

    @Test
    void validateOrganizationShouldReturnTrueIfExists() throws NoSuchAlgorithmException {
        assertTrue(organizationService.validateOrganization(organization.getEmail(), organization.getPassword()));
    }

    @Test
    public void testValidateOrganizationWithWrongEmail() throws NoSuchAlgorithmException {
        assertFalse(organizationService.validateOrganization("wrongEmailAdress@test.com",
                organization.getPassword()));
    }

    @Test
    public void testValidateOrganizationWithWrongPassword() throws NoSuchAlgorithmException {
        assertFalse(organizationService.validateOrganization(organization.getEmail(), "wrongPassword"));
    }

    @Test
    public void testDeleteOrganizationByNonExistentId() {
        assertThrows(RuntimeException.class, () -> {
            organizationService.deleteOrganizationById(124312412);  // An arbitrary non-existent ID
        });
    }

    @AfterAll
    public void tearDown() {
        organizationService.deleteOrganizationById(organizationFromDb.getId());
        assertFalse(organizationService.organizationExists(organizationFromDb.getEmail()));
    }
}
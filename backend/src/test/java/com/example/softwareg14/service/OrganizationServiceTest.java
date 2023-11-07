package com.example.softwareg14.service;

import com.example.softwareg14.config.DatabaseConfig;
import com.example.softwareg14.config.DaoModule;
import com.example.softwareg14.config.ServiceModule;
import com.example.softwareg14.model.Organization;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = {ServiceModule.class, DaoModule.class, DatabaseConfig.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
        organizationFromDb = organizationService.getOrganizationByEmail(organization.getEmail(), organization.getPassword());
    }

    @Test
    @Order(1)
    public void testCreatingAOrganizationThatAlreadyExistShouldThrowException() throws NoSuchAlgorithmException {
        assertThrows(IllegalArgumentException.class, () -> {
            organizationService.createOrganization(organization);
        });
    }

    @Test
    @Order(2)
    void createdOrganizationEmailShouldBeEqualToOrganizationFromDb() {
        assertEquals(organization.getEmail(), organizationFromDb.getEmail());
    }

    @Test
    @Order(3)
    void createdOrganizationNameShouldBeEqualToOrganizationFromDb() {
        assertEquals(organization.getName(), organizationFromDb.getName());
    }

    @Test
    @Order(4)
    void createdOrganizationAddressShouldBeEqualToOrganizationFromDb() {
        assertEquals(organization.getAddress(), organizationFromDb.getAddress());
    }

    @Test
    @Order(5)
    void createdOrganizationPhoneShouldBeEqualToOrganizationFromDb() {
        assertEquals(organization.getPhone(), organizationFromDb.getPhone());
    }

    @Test
    @Order(6)
    void createdOrganizationWebsiteShouldBeEqualToOrganizationFromDb() {
        assertEquals(organization.getWebsite(), organizationFromDb.getWebsite());
    }

    @Test
    @Order(7)
    void createdOrganizationDescriptionShouldBeEqualToOrganizationFromDb() {
        assertEquals(organization.getDescription(), organizationFromDb.getDescription());
    }

    @Test
    @Order(8)
    void updateOrganizationShouldUpdateTheCorrectValuesAndMatch() throws NoSuchAlgorithmException {
        organizationFromDb.setName("test8");
        organizationService.updateOrganization(organizationFromDb);
        organizationFromDb = organizationService.getOrganizationByEmail(organization.getEmail(), organization.getPassword());
        assertEquals("test8", organizationFromDb.getName());
    }

    @Test
    @Order(9)
    void getAllOrganizationsShouldBeAListLargerThanZero() {
        List<Organization> organizations = organizationService.getAllOrganizations();
        assertTrue(organizations.size() > 0);
        assertTrue(organizations.contains(organizationFromDb));
    }

    @Test
    @Order(10)
    void registeredOrganizationShouldBeFetchedFromDB() {
        Organization organizationById = organizationService.getOrganizationById(organizationFromDb.getId());
        assertEquals(organizationFromDb, organizationById);
    }

    @Test
    @Order(11)
    void registeredOrganizationShouldExist() {
        assertTrue(organizationService.organizationExists(organizationFromDb.getEmail()));
    }

    @Test
    @Order(12)
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
    @Order(13)
    public void testGetOrganizationByNonExistentId() {
        assertThrows(RuntimeException.class, () -> {
            organizationService.getOrganizationById(4121241);
        });
    }

    @Test
    @Order(14)
    void validateOrganizationShouldReturnTrueIfExists() throws NoSuchAlgorithmException {
        assertTrue(organizationService.validateOrganization(organization.getEmail(), organization.getPassword()));
    }

    @Test
    @Order(15)
    public void testValidateOrganizationWithWrongEmail() throws NoSuchAlgorithmException {
        assertFalse(organizationService.validateOrganization("wrongEmailAdress@test.com",
                organization.getPassword()));
    }

    @Test
    @Order(16)
    public void testValidateOrganizationWithWrongPassword() throws NoSuchAlgorithmException {
        assertFalse(organizationService.validateOrganization(organization.getEmail(), "wrongPassword"));
    }

    @Test
    @Order(17)
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
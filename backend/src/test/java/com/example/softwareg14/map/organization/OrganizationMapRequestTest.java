package com.example.softwareg14.map.organization;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationMapRequestTest {

    @Test
    public void allFieldsFilledShouldSuccess() {
        OrganizationRequest request = new OrganizationRequest();
        request.name = "name";
        request.address = "address";
        request.phone = "phone";
        request.email = "email";
        request.website = "website";
        request.description = "description";
        request.password = "password";
        assertTrue(request.validate());
    }

    @Test
    public void nameEmptyShouldFail() {
        OrganizationRequest request = new OrganizationRequest();
        request.name = "";
        request.address = "address";
        request.phone = "phone";
        request.email = "email";
        request.website = "website";
        request.description = "description";
        request.password = "password";
        assertFalse(request.validate());
    }

    @Test
    public void nameNullShouldFail() {
        OrganizationRequest request = new OrganizationRequest();
        request.name = null;
        request.address = "address";
        request.phone = "phone";
        request.email = "email";
        request.website = "website";
        request.description = "description";
        request.password = "password";
        assertFalse(request.validate());
    }

    @Test
    public void addressEmptyShouldFail() {
        OrganizationRequest request = new OrganizationRequest();
        request.name = "name";
        request.address = "";
        request.phone = "phone";
        request.email = "email";
        request.website = "website";
        request.description = "description";
        request.password = "password";
        assertFalse(request.validate());
    }

    @Test
    public void addressNullShouldFail() {
        OrganizationRequest request = new OrganizationRequest();
        request.name = "name";
        request.address = null;
        request.phone = "phone";
        request.email = "email";
        request.website = "website";
        request.description = "description";
        request.password = "password";
        assertFalse(request.validate());
    }

    @Test
    public void phoneEmptyShouldFail() {
        OrganizationRequest request = new OrganizationRequest();
        request.name = "name";
        request.address = "address";
        request.phone = "";
        request.email = "email";
        request.website = "website";
        request.description = "description";
        request.password = "password";
        assertFalse(request.validate());
    }

    @Test
    public void phoneNullShouldFail() {
        OrganizationRequest request = new OrganizationRequest();
        request.name = "name";
        request.address = "address";
        request.phone = null;
        request.email = "email";
        request.website = "website";
        request.description = "description";
        request.password = "password";
        assertFalse(request.validate());
    }

    @Test
    public void emailEmptyShouldFail() {
        OrganizationRequest request = new OrganizationRequest();
        request.name = "name";
        request.address = "address";
        request.phone = "phone";
        request.email = "";
        request.website = "website";
        request.description = "description";
        request.password = "password";
        assertFalse(request.validate());
    }

    @Test
    public void emailNullShouldFail() {
        OrganizationRequest request = new OrganizationRequest();
        request.name = "name";
        request.address = "address";
        request.phone = "phone";
        request.email = null;
        request.website = "website";
        request.description = "description";
        request.password = "password";
        assertFalse(request.validate());
    }

    @Test
    public void websiteEmptyShouldFail() {
        OrganizationRequest request = new OrganizationRequest();
        request.name = "name";
        request.address = "address";
        request.phone = "phone";
        request.email = "email";
        request.website = "";
        request.description = "description";
        request.password = "password";
        assertFalse(request.validate());
    }

    @Test
    public void websiteNullShouldFail() {
        OrganizationRequest request = new OrganizationRequest();
        request.name = "name";
        request.address = "address";
        request.phone = "phone";
        request.email = "email";
        request.website = null;
        request.description = "description";
        request.password = "password";
        assertFalse(request.validate());
    }

    @Test
    public void descriptionEmptyShouldFail() {
        OrganizationRequest request = new OrganizationRequest();
        request.name = "name";
        request.address = "address";
        request.phone = "phone";
        request.email = "email";
        request.website = "website";
        request.description = "";
        request.password = "password";
        assertFalse(request.validate());
    }

    @Test
    public void descriptionNullShouldFail() {
        OrganizationRequest request = new OrganizationRequest();
        request.name = "name";
        request.address = "address";
        request.phone = "phone";
        request.email = "email";
        request.website = "website";
        request.description = null;
        request.password = "password";
        assertFalse(request.validate());
    }

    @Test
    public void passwordEmptyShouldFail() {
        OrganizationRequest request = new OrganizationRequest();
        request.name = "name";
        request.address = "address";
        request.phone = "phone";
        request.email = "email";
        request.website = "website";
        request.description = "description";
        request.password = "";
        assertFalse(request.validate());
    }

    @Test
    public void passwordNullShouldFail() {
        OrganizationRequest request = new OrganizationRequest();
        request.name = "name";
        request.address = "address";
        request.phone = "phone";
        request.email = "email";
        request.website = "website";
        request.description = "description";
        request.password = null;
        assertFalse(request.validate());
    }
}
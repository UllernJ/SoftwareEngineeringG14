package com.example.softwareg14.controller.organization;

import com.example.softwareg14.controller.Error;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationControllerRequestTest {

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
        List<Error> errors = request.validate();
        assertTrue(errors.isEmpty());
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
        List<Error> errors = request.validate();
        assertTrue(errors.contains(Error.MISSING_NAME));
        assertEquals(1, errors.size());
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
        List<Error> errors = request.validate();
        assertTrue(errors.contains(Error.MISSING_NAME));
        assertEquals(1, errors.size());
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
        List<Error> errors = request.validate();
        assertTrue(errors.contains(Error.MISSING_ADDRESS));
        assertEquals(1, errors.size());
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
        List<Error> errors = request.validate();
        assertTrue(errors.contains(Error.MISSING_ADDRESS));
        assertEquals(1, errors.size());
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
        List<Error> errors = request.validate();
        assertTrue(errors.contains(Error.MISSING_PHONE));
        assertEquals(1, errors.size());
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
        List<Error> errors = request.validate();
        assertTrue(errors.contains(Error.MISSING_PHONE));
        assertEquals(1, errors.size());
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
        List<Error> errors = request.validate();
        assertTrue(errors.contains(Error.MISSING_EMAIL));
        assertEquals(1, errors.size());
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
        List<Error> errors = request.validate();
        assertTrue(errors.contains(Error.MISSING_EMAIL));
        assertEquals(1, errors.size());
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
        List<Error> errors = request.validate();
        assertTrue(errors.contains(Error.MISSING_WEBSITE));
        assertEquals(1, errors.size());
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
        List<Error> errors = request.validate();
        assertTrue(errors.contains(Error.MISSING_WEBSITE));
        assertEquals(1, errors.size());
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
        List<Error> errors = request.validate();
        assertTrue(errors.contains(Error.MISSING_DESCRIPTION));
        assertEquals(1, errors.size());
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
        List<Error> errors = request.validate();
        assertTrue(errors.contains(Error.MISSING_DESCRIPTION));
        assertEquals(1, errors.size());
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
        List<Error> errors = request.validate();
        assertTrue(errors.contains(Error.MISSING_PASSWORD));
        assertEquals(1, errors.size());
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
        List<Error> errors = request.validate();
        assertTrue(errors.contains(Error.MISSING_PASSWORD));
        assertEquals(1, errors.size());
    }
}
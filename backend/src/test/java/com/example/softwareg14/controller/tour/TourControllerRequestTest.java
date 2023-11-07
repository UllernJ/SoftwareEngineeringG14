package com.example.softwareg14.controller.tour;

import com.example.softwareg14.controller.Error;
import com.example.softwareg14.model.Organization;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TourControllerRequestTest {

    Organization organization = new Organization();

    @Test
    public void ValitadeShouldFailIfNameIsNull() {
        TourRequest tourRequest = new TourRequest();
        tourRequest.name = null;
        tourRequest.description = "description";
        tourRequest.image = "image";
        tourRequest.location = "location";
        tourRequest.category = "category";
        tourRequest.organization = organization;
        List<Error> errors = tourRequest.validate();
        assertTrue(errors.contains(Error.MISSING_NAME));
        assertEquals(1, errors.size());
    }

    @Test
    public void ValitadeShouldFailIfNameIsEmpty() {
        TourRequest tourRequest = new TourRequest();
        tourRequest.name = "";
        tourRequest.description = "description";
        tourRequest.image = "image";
        tourRequest.location = "location";
        tourRequest.category = "category";
        tourRequest.organization = organization;
        List<Error> errors = tourRequest.validate();
        assertTrue(errors.contains(Error.MISSING_NAME));
        assertEquals(1, errors.size());
    }

    @Test
    public void ValitadeShouldFailIfDescriptionIsNull() {
        TourRequest tourRequest = new TourRequest();
        tourRequest.name = "name";
        tourRequest.description = null;
        tourRequest.image = "image";
        tourRequest.location = "location";
        tourRequest.category = "category";
        tourRequest.organization = organization;
        List<Error> errors = tourRequest.validate();
        assertTrue(errors.contains(Error.MISSING_DESCRIPTION));
        assertEquals(1, errors.size());
    }

    @Test
    public void ValitadeShouldFailIfDescriptionIsEmpty() {
        TourRequest tourRequest = new TourRequest();
        tourRequest.name = "name";
        tourRequest.description = "";
        tourRequest.image = "image";
        tourRequest.location = "location";
        tourRequest.category = "category";
        tourRequest.organization = organization;
        List<Error> errors = tourRequest.validate();
        assertTrue(errors.contains(Error.MISSING_DESCRIPTION));
        assertEquals(1, errors.size());
    }

    @Test
    public void ValitadeShouldFailIfImageIsNull() {
        TourRequest tourRequest = new TourRequest();
        tourRequest.name = "name";
        tourRequest.description = "description";
        tourRequest.image = null;
        tourRequest.location = "location";
        tourRequest.category = "category";
        tourRequest.organization = organization;
        List<Error> errors = tourRequest.validate();
        assertTrue(errors.contains(Error.MISSING_IMAGE));
        assertEquals(1, errors.size());
    }

    @Test
    public void ValitadeShouldFailIfImageIsEmpty() {
        TourRequest tourRequest = new TourRequest();
        tourRequest.name = "name";
        tourRequest.description = "description";
        tourRequest.image = "";
        tourRequest.location = "location";
        tourRequest.category = "category";
        tourRequest.organization = organization;
        List<Error> errors = tourRequest.validate();
        assertTrue(errors.contains(Error.MISSING_IMAGE));
        assertEquals(1, errors.size());
    }

    @Test
    public void ValitadeShouldFailIfLocationIsNull() {
        TourRequest tourRequest = new TourRequest();
        tourRequest.name = "name";
        tourRequest.description = "description";
        tourRequest.image = "image";
        tourRequest.location = null;
        tourRequest.category = "category";
        tourRequest.organization = organization;
        List<Error> errors = tourRequest.validate();
        assertTrue(errors.contains(Error.MISSING_LOCATION));
        assertEquals(1, errors.size());
    }

    @Test
    public void ValitadeShouldFailIfLocationIsEmpty() {
        TourRequest tourRequest = new TourRequest();
        tourRequest.name = "name";
        tourRequest.description = "description";
        tourRequest.image = "image";
        tourRequest.location = "";
        tourRequest.category = "category";
        tourRequest.organization = organization;
        List<Error> errors = tourRequest.validate();
        assertTrue(errors.contains(Error.MISSING_LOCATION));
        assertEquals(1, errors.size());
    }

    @Test
    public void ValitadeShouldFailIfCategoryIsNull() {
        TourRequest tourRequest = new TourRequest();
        tourRequest.name = "name";
        tourRequest.description = "description";
        tourRequest.image = "image";
        tourRequest.location = "location";
        tourRequest.category = null;
        tourRequest.organization = organization;
        List<Error> errors = tourRequest.validate();
        assertTrue(errors.contains(Error.MISSING_CATEGORY));
        assertEquals(1, errors.size());
    }

    @Test
    public void ValitadeShouldFailIfCategoryIsEmpty() {
        TourRequest tourRequest = new TourRequest();
        tourRequest.name = "name";
        tourRequest.description = "description";
        tourRequest.image = "image";
        tourRequest.location = "location";
        tourRequest.category = "";
        tourRequest.organization = organization;
        List<Error> errors = tourRequest.validate();
        assertTrue(errors.contains(Error.MISSING_CATEGORY));
        assertEquals(1, errors.size());
    }

    @Test
    public void ValitadeShouldFailIfOrganizationIsNull() {
        TourRequest tourRequest = new TourRequest();
        tourRequest.name = "name";
        tourRequest.description = "description";
        tourRequest.image = "image";
        tourRequest.location = "location";
        tourRequest.category = "category";
        tourRequest.organization = null;
        List<Error> errors = tourRequest.validate();
        assertTrue(errors.contains(Error.MISSING_ORGANIZATION));
        assertEquals(1, errors.size());
    }
}

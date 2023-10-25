package com.example.softwareg14.controller.user;

import com.example.softwareg14.controller.Error;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerRequestTest {
    @Test
    public void validateShouldFailIfEmailIsNull() {
        UserRequest userRequest = new UserRequest();
        userRequest.email = null;
        userRequest.name = "name";
        userRequest.username = "username";
        userRequest.password = "password";
        List<Error> errors = userRequest.validate();
        assertTrue(errors.contains(Error.MISSING_EMAIL));
        assertEquals(1, errors.size());
    }
    @Test
    public void validateShouldFailIfEmailIsEmpty() {
        UserRequest userRequest = new UserRequest();
        userRequest.email = "";
        userRequest.name = "name";
        userRequest.username = "username";
        userRequest.password = "password";
        List<Error> errors = userRequest.validate();
        assertTrue(errors.contains(Error.MISSING_EMAIL));
        assertEquals(1, errors.size());
    }

    @Test
    public void validateShouldFailIfNameIsNull() {
        UserRequest userRequest = new UserRequest();
        userRequest.email = "email";
        userRequest.name = null;
        userRequest.username = "username";
        userRequest.password = "password";
        List<Error> errors = userRequest.validate();
        assertTrue(errors.contains(Error.MISSING_NAME));
        assertEquals(1, errors.size());
    }
    @Test
    public void validateShouldFailIfNameIsEmpty() {
        UserRequest userRequest = new UserRequest();
        userRequest.email = "email";
        userRequest.name = "";
        userRequest.username = "username";
        userRequest.password = "password";
        List<Error> errors = userRequest.validate();
        assertTrue(errors.contains(Error.MISSING_NAME));
        assertEquals(1, errors.size());
    }
    @Test
    public void validateShouldFailIfUsernameIsNull() {
        UserRequest userRequest = new UserRequest();
        userRequest.email = "email";
        userRequest.name = "name";
        userRequest.username = null;
        userRequest.password = "password";
        List<Error> errors = userRequest.validate();
        assertTrue(errors.contains(Error.MISSING_USERNAME));
        assertEquals(1, errors.size());
    }
    @Test
    public void validateShouldFailIfUsernameIsEmpty() {
        UserRequest userRequest = new UserRequest();
        userRequest.email = "email";
        userRequest.name = "name";
        userRequest.username = "";
        userRequest.password = "password";
        List<Error> errors = userRequest.validate();
        assertTrue(errors.contains(Error.MISSING_USERNAME));
        assertEquals(1, errors.size());
    }
    @Test
    public void validateShouldFailIfPasswordIsNull() {
        UserRequest userRequest = new UserRequest();
        userRequest.email = "email";
        userRequest.name = "name";
        userRequest.username = "username";
        userRequest.password = null;
        List<Error> errors = userRequest.validate();
        assertTrue(errors.contains(Error.MISSING_PASSWORD));
        assertEquals(1, errors.size());
    }
    @Test
    public void validateShouldFailIfPasswordIsEmpty() {
        UserRequest userRequest = new UserRequest();
        userRequest.email = "email";
        userRequest.name = "name";
        userRequest.username = "username";
        userRequest.password = "";
        List<Error> errors = userRequest.validate();
        assertTrue(errors.contains(Error.MISSING_PASSWORD));
        assertEquals(1, errors.size());
    }
    @Test
    public void validateShouldPassIfAllFieldsAreFilled() {
        UserRequest userRequest = new UserRequest();
        userRequest.email = "email";
        userRequest.name = "name";
        userRequest.username = "username";
        userRequest.password = "password";
        List<Error> errors = userRequest.validate();
        assertTrue(errors.isEmpty());
    }

}
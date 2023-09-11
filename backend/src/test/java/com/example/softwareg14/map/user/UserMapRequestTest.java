package com.example.softwareg14.map.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapRequestTest {

    //create tests for validate method with all possible cases
    @Test
    public void validateShouldFailIfEmailIsNull() {
        UserRequest userRequest = new UserRequest();
        userRequest.email = null;
        userRequest.name = "name";
        userRequest.username = "username";
        userRequest.password = "password";
        assertFalse(userRequest.validate());
    }
    @Test
    public void validateShouldFailIfEmailIsEmpty() {
        UserRequest userRequest = new UserRequest();
        userRequest.email = "";
        userRequest.name = "name";
        userRequest.username = "username";
        userRequest.password = "password";
        assertFalse(userRequest.validate());
    }

    @Test
    public void validateShouldFailIfNameIsNull() {
        UserRequest userRequest = new UserRequest();
        userRequest.email = "email";
        userRequest.name = null;
        userRequest.username = "username";
        userRequest.password = "password";
        assertFalse(userRequest.validate());
    }
    @Test
    public void validateShouldFailIfNameIsEmpty() {
        UserRequest userRequest = new UserRequest();
        userRequest.email = "email";
        userRequest.name = "";
        userRequest.username = "username";
        userRequest.password = "password";
        assertFalse(userRequest.validate());
    }
    @Test
    public void validateShouldFailIfUsernameIsNull() {
        UserRequest userRequest = new UserRequest();
        userRequest.email = "email";
        userRequest.name = "name";
        userRequest.username = null;
        userRequest.password = "password";
        assertFalse(userRequest.validate());
    }
    @Test
    public void validateShouldFailIfUsernameIsEmpty() {
        UserRequest userRequest = new UserRequest();
        userRequest.email = "email";
        userRequest.name = "name";
        userRequest.username = "";
        userRequest.password = "password";
        assertFalse(userRequest.validate());
    }
    @Test
    public void validateShouldFailIfPasswordIsNull() {
        UserRequest userRequest = new UserRequest();
        userRequest.email = "email";
        userRequest.name = "name";
        userRequest.username = "username";
        userRequest.password = null;
        assertFalse(userRequest.validate());
    }
    @Test
    public void validateShouldFailIfPasswordIsEmpty() {
        UserRequest userRequest = new UserRequest();
        userRequest.email = "email";
        userRequest.name = "name";
        userRequest.username = "username";
        userRequest.password = "";
        assertFalse(userRequest.validate());
    }
    @Test
    public void validateShouldPassIfAllFieldsAreFilled() {
        UserRequest userRequest = new UserRequest();
        userRequest.email = "email";
        userRequest.name = "name";
        userRequest.username = "username";
        userRequest.password = "password";
        assertTrue(userRequest.validate());
    }

}
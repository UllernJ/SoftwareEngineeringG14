package com.example.softwareg14.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void userWithEmptyNameIsInvalid() {
        User user = new User();
        user.setName("");
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");
        assertFalse(user.validate());
    }
    @Test
    public void userWithEmptyUsernameIsInvalid() {
        User user = new User();
        user.setName("name");
        user.setUsername("");
        user.setPassword("password");
        user.setEmail("email");
        assertFalse(user.validate());
    }

    @Test
    public void userWithEmptyPasswordIsInvalid() {
        User user = new User();
        user.setName("name");
        user.setUsername("username");
        user.setPassword("");
        user.setEmail("email");
        assertFalse(user.validate());
    }

    @Test
    public void userWithEmptyEmailIsInvalid() {
        User user = new User();
        user.setName("name");
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("");
        assertFalse(user.validate());
    }
    @Test
    public void userWithAllFieldsIsValid() {
        User user = new User();
        user.setName("name");
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");
        assertTrue(user.validate());
    }

}
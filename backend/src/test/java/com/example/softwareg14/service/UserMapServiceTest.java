package com.example.softwareg14.service;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class UserMapServiceTest {

    @Test
    public void testHashPassword() throws NoSuchAlgorithmException {
        UserService userService = new UserService(null);
        String password = "batman14";
        String hashedPassword = "cbbc30ec174736b13ef4853e7fd4764f";
        assertEquals(hashedPassword, userService.hashPassword(password));
    }

}
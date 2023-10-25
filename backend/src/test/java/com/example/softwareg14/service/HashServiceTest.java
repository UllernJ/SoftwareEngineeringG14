package com.example.softwareg14.service;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class HashServiceTest {

    @Test
    public void hashedPasswordShouldEqualToPreHashed() throws NoSuchAlgorithmException {
        String password = "batman14";
        String hashedPassword = "cbbc30ec174736b13ef4853e7fd4764f";
        assertEquals(hashedPassword, HashService.hashPassword(password));
    }

    @Test
    public void testHashPasswordShouldNotBeEqual() throws NoSuchAlgorithmException {
        String password = "batman14";
        String hashedPassword = "cbbc30ec174736b13ef4853e7fd4764f";
        assertNotEquals(hashedPassword, HashService.hashPassword(password + "1"));
    }


}
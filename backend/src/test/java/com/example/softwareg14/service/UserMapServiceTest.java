package com.example.softwareg14.service;

import com.example.softwareg14.config.AppConfig;
import com.example.softwareg14.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

@Service
class UserMapServiceTest {
    private final UserService userService;

    @Autowired
    public UserMapServiceTest() {
        AppConfig appConfig = new AppConfig();
        JdbcTemplate jdbcTemplate = appConfig.jdbcTemplate(appConfig.sqliteDataSource());
        UserDao userDao = new UserDao(jdbcTemplate);
        this.userService = new UserService(userDao);
    }

    @Test
    public void testHashPassword() throws NoSuchAlgorithmException {
        String password = "batman14";
        String hashedPassword = "cbbc30ec174736b13ef4853e7fd4764f";
        assertEquals(hashedPassword, HashService.hashPassword(password));
    }

    @Test
    public void userTestShouldExistInDb() {
        String username = "test";
        assertTrue(userService.userExist(username));
    }
    @Test
    public void userShouldNotExistInDb() {
        assertFalse(userService.userExist("jksahdusadhjashdusaujdkajhaAHJKJSAHJKAd"));
    }

}
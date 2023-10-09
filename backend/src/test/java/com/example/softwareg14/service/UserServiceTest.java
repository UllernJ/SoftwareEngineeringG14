package com.example.softwareg14.service;

import com.example.softwareg14.config.AppConfigTest;
import com.example.softwareg14.entity.User;
import org.junit.jupiter.api.*;


import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    private final UserService userService = new AppConfigTest().userService();
    private User user;
    private User userFromDb;


    @BeforeAll
    public void setUp() throws NoSuchAlgorithmException {
        user = User.builder()
                .username("test1")
                .name("test2")
                .email("test3")
                .password("test4")
                .build();
        userService.createUser(user);
        userFromDb = userService.getUserByUsername(user.getUsername());
        assertEquals(user.getUsername(), userFromDb.getUsername());
    }

    @Test
    public void testCreatingAUserThatAlreadyExistShouldThrowException() throws NoSuchAlgorithmException {
        assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(user);
        });
    }

    @Test
    public void testUserShouldExist() {
        String username = user.getUsername();
        assertTrue(userService.userExist(username));
    }

    @Test
    public void testUserShouldNotExist() {
        String username = "test1234ik12j4io1jdisadasioasdadasdasdasva";
        assertFalse(userService.userExist(username));
    }

    @Test
    public void testUserShouldBeValid() throws NoSuchAlgorithmException {
        String username = user.getUsername();
        String password = user.getPassword();
        assertTrue(userService.validateUser(username, password));
    }

    @Test
    public void testUserShouldNotBeValid() throws NoSuchAlgorithmException {
        String username = user.getUsername();
        String password = "askodosakdopasdoaskdosadasldpaxplapxlpaslxpaslxa";
        assertFalse(userService.validateUser(username, password));
    }

    @Test
    public void testUserFromDbShouldHaveSameValuesAsUser() {
        assertEquals(user.getUsername(), userFromDb.getUsername());
        assertEquals(user.getName(), userFromDb.getName());
        assertEquals(user.getEmail(), userFromDb.getEmail());
    }

    @AfterAll
    public void tearDown() {
        userService.deleteUserById(userFromDb.getId());
        assertFalse(userService.userExist(user.getUsername()));
    }

}
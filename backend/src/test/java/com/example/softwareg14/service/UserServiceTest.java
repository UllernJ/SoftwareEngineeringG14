package com.example.softwareg14.service;

import com.example.softwareg14.config.AppConfig;
import com.example.softwareg14.config.DaoModule;
import com.example.softwareg14.config.ServiceModule;
import com.example.softwareg14.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = { ServiceModule.class, DaoModule.class, AppConfig.class })
public class UserServiceTest {

    @Autowired
    private UserService userService;
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
    public void testUserCreatedShouldExistInDb() {
        String username = user.getUsername();
        assertTrue(userService.userExist(username));
    }

    @Test
    public void testUserShouldNotExistInDb() {
        String username = "test1234ik12j4io1jdisadasioasdadasdasdasva";
        assertFalse(userService.userExist(username));
    }

    @Test
    public void testValidateUserShouldBeTrueIfExistsInDB() throws NoSuchAlgorithmException {
        String username = user.getUsername();
        String password = "test4";
        assertTrue(userService.validateUser(username, password));
    }

    @Test
    public void testValidateUserShouldBeFalseIfNotExistsInDB() throws NoSuchAlgorithmException {
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

    @Test
    public void testUpdateUserShouldNotWorkIfDoesntExist() {
        User user = User.builder()
                .username("test1234ik12j4io1jdisadasioasdadasdasdasva")
                .name("test2")
                .email("test3")
                .password("test4")
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            userService.updateUser(user);
        });
    }

    @Test
    public void testUpdateUserShouldWorkIfExist() throws NoSuchAlgorithmException {
        String oldName = user.getName();
        user.setName("NEW NAME");
        userService.updateUser(user);
        User userFromDb = userService.getUserByUsername(user.getUsername());

        assertNotEquals(oldName, userFromDb.getName());
        assertEquals(user.getName(), userFromDb.getName());
    }

    @Test
    public void testIfUserIdIsNullItWillBeFetchedWhenUpdating() throws NoSuchAlgorithmException {
        user.setId(null);
        userService.updateUser(user);
        assertNotNull(user.getId());
    }

    @Test
    public void testDeleteUserByIdShouldThrowExceptionIfUserDoesNotExist() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            userService.deleteUserById(123123);
        });
    }

    @AfterAll
    public void tearDown() {
        userService.deleteUserById(userFromDb.getId());
        assertFalse(userService.userExist(user.getUsername()));
    }

}
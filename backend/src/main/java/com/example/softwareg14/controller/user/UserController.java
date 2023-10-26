package com.example.softwareg14.controller.user;

import com.example.softwareg14.controller.Endpoint;
import com.example.softwareg14.controller.Error;
import com.example.softwareg14.model.User;
import com.example.softwareg14.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(Endpoint.USER_BY_ID)
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        try {
            User user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(Endpoint.USER_REGISTER)
    public ResponseEntity<String> register(@RequestBody UserRequest userRequest) {
        List<Error> errors = userRequest.validate();
        if (!errors.isEmpty()) {
            return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
        }
        try {
            User user = User.builder()
                    .name(userRequest.name)
                    .username(userRequest.username)
                    .password(userRequest.password)
                    .email(userRequest.email)
                    .build();
            userService.createUser(user);
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to register user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(Endpoint.USER_UPDATE)
    public ResponseEntity<String> update(@RequestBody UserRequest userRequest) {
        List<Error> errors = userRequest.validate();
        if (!errors.isEmpty()) {
            return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
        }
        try {
            User user = User.builder()
                    .name(userRequest.name)
                    .username(userRequest.username)
                    .password(userRequest.password)
                    .email(userRequest.email)
                    .build();
            userService.updateUser(user);
            return new ResponseEntity<>("User updated successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(Endpoint.USER_DELETE)
    public ResponseEntity<String> delete(@RequestBody UserRequest userRequest) {
        List<Error> errors = userRequest.validate();
        if (!errors.isEmpty()) {
            return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
        }
        try {
            userService.deleteUserById(userRequest.id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(Endpoint.USER_LOGIN)
    public ResponseEntity<User> login(@RequestBody UserRequest userRequest) {
        try {
            if (userService.validateUser(userRequest.username, userRequest.password)) {
                User user = userService.getUserByUsername(userRequest.username);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.example.softwareg14.map.user;

import com.example.softwareg14.map.Endpoint;
import com.example.softwareg14.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class User {

    private final UserService userService;

    @Autowired
    public User(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(Endpoint.USER_REGISTER)
    public ResponseEntity<String> user(@RequestBody UserRequest userRequest) {
        if (!userRequest.validate()) {
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
        try {
            userService.createUser(userRequest.name, userRequest.username, userRequest.password, userRequest.email);
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to register user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.example.softwareg14.map;

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
    @PostMapping("/api/user/register")
    public ResponseEntity<String> user(@RequestBody UserRequest userRequest) {
        System.out.println("Registering user");
        try {
            userService.createUser(userRequest.name, userRequest.username, userRequest.password, userRequest.email);
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to register user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

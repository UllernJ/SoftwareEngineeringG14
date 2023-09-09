package com.example.softwareg14.map;

import com.example.softwareg14.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    private final UserService userService;

    @Autowired
    public Test(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/test")
    public String test() {
        return "test";
    }
}

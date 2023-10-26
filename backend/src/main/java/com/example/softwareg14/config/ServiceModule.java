package com.example.softwareg14.config;

import com.example.softwareg14.service.OrganizationService;
import com.example.softwareg14.service.TourService;
import com.example.softwareg14.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceModule {

    @Bean
    public OrganizationService organizationService() {
        return new OrganizationService();
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public TourService tourService() {
        return new TourService();
    }

}


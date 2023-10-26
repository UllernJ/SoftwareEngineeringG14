package com.example.softwareg14.config;

import com.example.softwareg14.dao.OrganizationDao;
import com.example.softwareg14.dao.TourDao;
import com.example.softwareg14.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoModule {
    @Bean
    public UserDao userDao() {
        return new UserDao();
    }

    @Bean
    public TourDao tourDao() {
        return new TourDao();
    }

    @Bean
    public OrganizationDao organizationDao() {
        return new OrganizationDao();
    }

}

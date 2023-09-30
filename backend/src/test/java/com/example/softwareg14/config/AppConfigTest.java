package com.example.softwareg14.config;

import com.example.softwareg14.dao.UserDao;
import com.example.softwareg14.service.UserService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@TestConfiguration
public class AppConfigTest {

    private final AppConfig appConfig = new AppConfig();

    @Bean
    public DataSource sqliteDataSource() {
        return appConfig.sqliteDataSource();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return appConfig.jdbcTemplate(sqliteDataSource());
    }

    @Bean
    public UserDao userDao() {
        return new UserDao(jdbcTemplate());
    }

    @Bean
    public UserService userService() {
        return new UserService(userDao());
    }



}
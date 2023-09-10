package com.example.softwareg14.service;

import com.example.softwareg14.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createUser(String name, String username, String password) {
        jdbcTemplate.update("INSERT INTO user (name, username, password) VALUES (?, ?, ?)", name, username, password);
    }

    public User getUserById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM user WHERE id = ?", new Object[]{id}, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        });
    }
    public void deleteUserById(int id) {
        jdbcTemplate.update("DELETE FROM user WHERE id = ?", id);
    }

}

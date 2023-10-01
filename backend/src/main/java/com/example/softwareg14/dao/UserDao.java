package com.example.softwareg14.dao;

import com.example.softwareg14.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao implements Dao<User> {

    private List<User> users;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM user WHERE id = ?", new Object[]{id}, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            return user;
        });
    }

    @Override
    public void create(User user) {
        jdbcTemplate.update("INSERT INTO user (name, username, password, email) VALUES (?, ?, ?, ?)", user.getName(), user.getUsername(), user.getPassword(), user.getEmail());
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM user WHERE id = ?", id);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    public boolean validateUser(String username, String password) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user WHERE username = ? AND password = ?", new Object[]{username, password}, Integer.class) > 0;
    }

    public User getUserByUsername(String username) {
        return jdbcTemplate.queryForObject("SELECT * FROM user WHERE username = ?", new Object[]{username}, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            return user;
        });
    }

    public boolean userExist(String username) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user WHERE username = ?", new Object[]{username}, Integer.class) > 0;
    }
}

package com.example.softwareg14.dao;

import com.example.softwareg14.entity.Role;
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
        return jdbcTemplate.queryForObject("SELECT * FROM user WHERE id = ?", (rs, rowNum) -> {
            User user = User.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .username(rs.getString("username"))
                    .password(rs.getString("password"))
                    .email(rs.getString("email"))
                    .role(Role.valueOf(rs.getString("role")))
                    .build();
            return user;
        }, id);
    }

    @Override
    public void create(User user) {
        jdbcTemplate.update("INSERT INTO user (name, username, password, email, role) VALUES (?, ?, ?, ?, 'USER')", user.getName(), user.getUsername(), user.getPassword(), user.getEmail());
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update("UPDATE user SET name = ?, username = ?, password = ?, email = ? WHERE id = ?", user.getName(), user.getUsername(), user.getPassword(), user.getEmail(), user.getId());
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
            user.setRole(Role.valueOf(rs.getString("role")));
            return user;
        });
    }

    public boolean userExist(String username) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user WHERE username = ?", new Object[]{username}, Integer.class) > 0;
    }
}

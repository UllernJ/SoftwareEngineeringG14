package com.example.softwareg14.service;

import com.example.softwareg14.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    private static final String SALT = "sajdhakjss2131zzklap";
    private static final String ALGORITHM = "MD5";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createUser(String name, String username, String password, String email) throws NoSuchAlgorithmException {
        if(userExist(username)) {
            throw new IllegalArgumentException("User already exists");
        }
        password = hashPassword(password);
        jdbcTemplate.update("INSERT INTO user (name, username, password, email) VALUES (?, ?, ?, ?)", name, username, password, email);
    }

    public boolean validateUser(String username, String password) throws NoSuchAlgorithmException {
        password = hashPassword(password);
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

    public User getUserById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM user WHERE id = ?", new Object[]{id}, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            return user;
        });
    }
    public void deleteUserById(int id) {
        jdbcTemplate.update("DELETE FROM user WHERE id = ?", id);
    }

    public boolean userExist(String username) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user WHERE username = ?", new Object[]{username}, Integer.class) > 0;
    }





    //from https://www.geeksforgeeks.org/md5-hash-in-java/ and https://mkyong.com/java/java-md5-hashing-example/
    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update((password + SALT).getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
        }

}

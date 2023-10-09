package com.example.softwareg14.service;

import com.example.softwareg14.dao.UserDao;
import com.example.softwareg14.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;


@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createUser(User user) throws NoSuchAlgorithmException {
        if (userExist(user.getUsername())) {
            throw new IllegalArgumentException("User already exists");
        }
        user.setPassword(HashService.hashPassword(user.getPassword()));
        userDao.create(user);
    }

    public boolean validateUser(String username, String password) throws NoSuchAlgorithmException {
        String hashedPassword = HashService.hashPassword(password);
        return userDao.validateUser(username, hashedPassword);
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public User getUserById(int id) {
        return userDao.getById(id);
    }

    public void deleteUserById(int id) {
        if(getUserById(id) == null) {
            throw new IllegalArgumentException("User does not exist");
        }
        userDao.delete(id);
    }

    public void updateUser(User user) throws NoSuchAlgorithmException {
        if(!userExist(user.getUsername())) {
            throw new IllegalArgumentException("User does not exist");
        }
        if(user.getId() == null) {
            int id = userDao.getUserByUsername(user.getUsername()).getId();
            user.setId(id);
        }
        user.setPassword(HashService.hashPassword(user.getPassword()));
        userDao.update(user);
    }

    public void deleteUser(int id) {
        userDao.delete(id);
    }

    public boolean userExist(String username) {
        return userDao.userExist(username);
    }
}

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

    public void createUser(String name, String username, String password, String email) throws NoSuchAlgorithmException {
        if(userExist(username)) {
            throw new IllegalArgumentException("User already exists"); //this should be handled before coming to this function.
        }
        password = HashService.hashPassword(password);
        User user = new User(name, username, password, email);
        userDao.create(user);
    }

    public boolean validateUser(String username, String password) throws NoSuchAlgorithmException {
        password = HashService.hashPassword(password);
        return userDao.validateUser(username, password);
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public User getUserById(int id) {
        return userDao.getById(id);
    }

    public void deleteUserById(int id) {
        userDao.delete(id);
    }

    public boolean userExist(String username) {return userDao.userExist(username);}
}

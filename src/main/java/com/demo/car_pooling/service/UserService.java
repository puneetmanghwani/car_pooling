package com.demo.car_pooling.service;

import com.demo.car_pooling.exception.UserException;
import com.demo.car_pooling.model.User;

import java.util.List;

public interface UserService {

    public User addUser(User user) throws UserException;

    public User getUserById(String userId) throws UserException;

    public List<User> getAllUsers();

}

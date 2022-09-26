package com.demo.car_pooling.service;

import com.demo.car_pooling.exception.UserException;
import com.demo.car_pooling.model.User;
import com.demo.car_pooling.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) throws UserException {

        User existingUser = userRepository.findByEmail(user.getEmail());

        if(existingUser!=null){
            throw new UserException("User Already Exist with given email " + user.getEmail());
        }

        return userRepository.saveUser(user);

    }

    @Override
    public User getUserById(String userId) throws UserException {
        User user = userRepository.findUserById(userId);

        if(user!=null){
            throw new UserException("User Already Exist with given email " + user.getEmail());
        }

        return user;
    }

    @Override
    public List<User> getAllUsers(){

        return userRepository.findAllUsers();

    }

}

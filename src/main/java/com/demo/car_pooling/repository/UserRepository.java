package com.demo.car_pooling.repository;

import com.demo.car_pooling.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserRepository {

    private List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    public synchronized User saveUser(User user){

        UUID uuid = UUID.randomUUID();
        user.setId(uuid.toString());

        this.users.add(user);

        return user;

    }

    public synchronized User findByEmail(String email){

        for(User user : this.users){
            if(user.getEmail().equals(email)){
                return user;
            }
        }

        return null;

    }

    public User findUserById(String userId){

        for(User user : this.users){
            if(user.getId().equals(userId)){
                return user;
            }
        }

        return null;

    }

}

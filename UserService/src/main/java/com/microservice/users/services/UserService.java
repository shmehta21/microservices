package com.microservice.users.services;

import com.microservice.users.entities.User;

import java.util.List;

public interface UserService {

    //Save a user
    User saveUser(User user);

    // Get All Users
    List<User> getAllUser();

    //get Single user
    User getUser(String userId);

    //Delete user
    User deleteUser(String userId);

    //Update user
    User updateUser(String userId);

}


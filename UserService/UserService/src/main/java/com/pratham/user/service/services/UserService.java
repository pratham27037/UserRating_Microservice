package com.pratham.user.service.services;

import com.pratham.user.service.entities.User;

import java.util.List;

public interface UserService {

    //to create
    User saveUser(User user);

    //to get all
    List<User> getAllUser();

    //to get user by id
    User getUser(String userId);

    //delete and update later to do by my self
}

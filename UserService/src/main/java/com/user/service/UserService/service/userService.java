package com.user.service.UserService.service;

import java.util.List;

import com.user.service.UserService.entities.User;

public interface userService {

    //create user /update also
    User saveUser(User u);

    // update user
    User updateuserById(String userId , User u);


    //get all user 
    List<User> getAllUser();


    // get user by id 
    User getUserById(String userId);


    //delete user by id 
    List<User> deleteUserById(String userId);

}

package com.microservice.users.controller;

import com.microservice.users.entities.User;
import com.microservice.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){

        User createdUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{userId}")
    public  ResponseEntity<User> getSingleUser(@PathVariable String userId){
        User foundUser = userService.getUser(userId);
        return ResponseEntity.ok(foundUser);
    }

    @GetMapping
    public  ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userService.getAllUser();
        return ResponseEntity.ok(allUsers);
    }
}

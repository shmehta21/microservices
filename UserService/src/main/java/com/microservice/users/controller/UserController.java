package com.microservice.users.controller;

import com.microservice.users.entities.User;
import com.microservice.users.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
    @CircuitBreaker(name="userToRatingAndHotelService", fallbackMethod = "userToRatingAndHotelServiceFallback")
    public  ResponseEntity<User> getSingleUser(@PathVariable String userId){
        User foundUser = userService.getUser(userId);
        return ResponseEntity.ok(foundUser);
    }

    //fallback method
    private ResponseEntity<User> userToRatingAndHotelServiceFallback(String userId, Exception ex){
        log.info("Fallback executed as the dependent services are down: ", ex.getMessage());
        return new ResponseEntity<>(User.builder()
                .email("dummy@email.com")
                .name("dummy")
                .about("returning dummy user")
                .userId("dummy123")
                .build(), HttpStatus.OK);
    }

    @GetMapping
    public  ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userService.getAllUser();
        return ResponseEntity.ok(allUsers);
    }
}

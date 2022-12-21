package com.microservice.users.services.impl;

import com.microservice.users.entities.Hotel;
import com.microservice.users.entities.Rating;
import com.microservice.users.entities.User;
import com.microservice.users.exceptions.ResourceNotFoundException;
import com.microservice.users.repository.UserRepository;
import com.microservice.users.services.HotelService;
import com.microservice.users.services.RatingService;
import com.microservice.users.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Resource with given Id:"+userId+ " not found on server!"));
        //fetch ratings given by the userId
        log.info("Fetching ratings from ratings service");
        //Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+userId, Rating[].class);
        List<Rating> ratings = ratingService.getRatings(userId);

        //List<Rating> ratingList = Arrays.stream(ratings).collect(Collectors.toList());
        List<Rating> hotelRatings = ratings.stream().map( rating -> {
            //ResponseEntity<Hotel> hotelResponse = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            //Hotel hotel = hotelResponse.getBody();
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(hotelRatings);
        return user;
    }

    @Override
    public User deleteUser(String userId) {
        return null;
    }

    @Override
    public User updateUser(String userId) {
        return null;
    }
}

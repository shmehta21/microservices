package com.microservice.rating.service;

import com.microservice.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating create(Rating rating);

    //get all ratings
    List<Rating> getRatings();

    //get all ratings by user Id
    List<Rating> getRatingByUserId(String userId);

    //get all ratings for a hotel
    List<Rating> getRatingByHotelId(String hotelId);



}

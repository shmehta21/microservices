package com.microservice.rating.repository;

import com.microservice.rating.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating, String> {

    //custom finder methods

    //return all ratings given by a user
    List<Rating> findByUserId(String userId);

    //return ratings for a hotel
    List<Rating> findByHotelId(String hotelId);
}

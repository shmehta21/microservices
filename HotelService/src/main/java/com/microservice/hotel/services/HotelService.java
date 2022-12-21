package com.microservice.hotel.services;

import com.microservice.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    //create
    Hotel create(Hotel hotel);

    //getAll
    List<Hotel> getAll();

    //get single
    Hotel get(String id);
}

package com.pratham.rating.services;

import com.pratham.rating.entities.Rating;

import java.util.List;

public interface RatingService {
    //create
    Rating create(Rating rating);

    // get all ratings
    List<Rating> getRatings();

    //get all bu userId
    List<Rating> getRatingsByUserId(String userId);

    //get all by hotel
    List<Rating> getRatingsByHotelId(String hotelId);
}

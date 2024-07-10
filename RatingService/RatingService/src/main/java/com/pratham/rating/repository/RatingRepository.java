package com.pratham.rating.repository;

import com.pratham.rating.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating,String> {
    //customs finders methods
    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);
}

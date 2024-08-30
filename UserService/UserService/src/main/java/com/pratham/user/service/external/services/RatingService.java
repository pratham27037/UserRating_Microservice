package com.pratham.user.service.external.services;

import com.pratham.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "RatingService")
public interface RatingService {

    //get

    //post
    @PostMapping("/ratings")
    public Rating createRating(Rating values); // will be send like json in format of rating
    // public ResponseEntity<Rating> createRating(Rating values); can use this too forr more info
    //put
    @PutMapping("/ratings/{ratingId")
    public Rating updateRating(@PathVariable("ratingId") String ratingId,Rating rating);

    //similary for the other methods
    // for checking only we can call the method anywhere even in the test class
}

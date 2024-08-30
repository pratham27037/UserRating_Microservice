package com.pratham.user.service.services.impl;

import com.pratham.user.service.entities.Hotel;
import com.pratham.user.service.entities.Rating;
import com.pratham.user.service.entities.User;
import com.pratham.user.service.exception.ResourceNotFoundException;
import com.pratham.user.service.external.services.HotelService;
import com.pratham.user.service.repositories.UserRepositories;
import com.pratham.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositories userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService; // injecting the feign client for hotel microservice from the interface created in this service

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        //generate unique userid
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        //implement rating service call using rest template
        return userRepository.findAll();
    }

    //get single user
    @Override
    public User getUser(String userId) {
        //get user from database with help of user repository
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("resource with given id is not found on the serve !! :" + userId));
        //fetch rating for the above user from rating service
        //http://localhost:8083/ratings/users/1e279ebc-ba0e-4c78-add1-427894b42f01

        Rating[] ratingsOfUSer = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info("{}",ratingsOfUSer);
        List<Rating> ratings = Arrays.stream(ratingsOfUSer).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel serivce to get hotel
            //http://localhost:8082/hotels/f7bc48f7-1659-40ff-8a67-5b1f6b2f5fef
            //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            //Hotel hotel = forEntity.getBody();
            Hotel hotel = hotelService.getHotel(rating.getHotelId()); // getting from external service Hotelservice through frign client
            //logger.info("response status code",forEntity.getStatusCode());
            //set the hotel to rating
            rating.setHotel(hotel);
            //return the rating
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }
}
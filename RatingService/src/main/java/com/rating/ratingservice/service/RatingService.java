package com.rating.ratingservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rating.ratingservice.entity.Rating;
import com.rating.ratingservice.repository.RatingRepository;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingrepository;

    //create rating
    public Rating createRating(Rating rate){
        String id = UUID.randomUUID().toString();
        rate.setRatingId(id);
            return ratingrepository.save(rate);
        
    }

    // get all rating
    public List<Rating> getAllRating(){
            return ratingrepository.findAll();
        
    }

    //get all rating by userid
    public List<Rating> getRatingByUserId(String userId){
        return ratingrepository.findByUserId(userId);
    
    }

    // get all rating by hotel 
    public List<Rating> getRatingByHotelId(String hotelId){
        return ratingrepository.findByHotelId(hotelId);
    }

}

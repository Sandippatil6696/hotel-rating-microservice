package com.rating.ratingservice.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rating.ratingservice.entity.Rating;
import com.rating.ratingservice.service.RatingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/rating")
public class RatingRestController {

    @Autowired
    private RatingService ratingService;
 
    //create rating 
    @PostMapping
    public ResponseEntity<Rating> createRate(@RequestBody Rating rate){
       return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(rate));
    }

    // getall rating 
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRating() {
        return ResponseEntity.ok(ratingService.getAllRating());
    }
    
    // get rating by user id 
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingOfUser(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    // get rating by hotel id 

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingOfHotel(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }
    
    

}

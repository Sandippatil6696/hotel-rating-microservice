package com.user.service.UserService.impl;



import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.UserService.entities.Hotel;
import com.user.service.UserService.entities.Rating;
import com.user.service.UserService.entities.User;
import com.user.service.UserService.exceptions.ResourceNotFoundException;
import com.user.service.UserService.externalservices.HotelService;
import com.user.service.UserService.repository.userRepository;
import com.user.service.UserService.service.userService;

@Service
public class userServiceImpl implements userService{

    @Autowired
    private userRepository userrepo ;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelservice ;
    
    @Override
    public User saveUser(User u) {
        // this one created a uniq user id 
        String id =  UUID.randomUUID().toString();
        u.setUserId(id);
        return userrepo.save(u);
    }

    @Override
    public List<User> getAllUser() {
        return userrepo.findAll();
    }

    @Override
    public User getUserById(String userId) {
        // get user from db using userrepository
        User user = userrepo.findById(userId)
        .orElseThrow(()->new ResourceNotFoundException("enter usrer id is not found in server , id is "+ userId));
       
        // get rating from rating microservice 
      Rating[] rate = restTemplate.getForObject("http://RATINGSERVICE/rating/user/"+user.getUserId(), Rating[].class);

      //    get hotel from hotel service by id 
        List<Rating> ratings =  Arrays.stream(rate).toList();
        
        List<Rating> ratingList = ratings.stream().map(rating -> {

            /*  api call to hotl service  to get hotel
                 ResponseEntity<Hotel> ent = restTemplate.getForEntity("http://HOTELSERVICE/hotel/"+rating.getHotelId(),Hotel.class);
                 Hotel hotel = ent.getBody();
            */

            Hotel hotel = hotelservice.getHotel(rating.getHotelId());


            // set hotel to Rating
            rating.setHotel(hotel);

            return rating ;

        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user ;
    }


    @Override
    public List<User> deleteUserById(String userId) {
         userrepo.deleteById(userId);
         return getAllUser();
        
    }

    @Override
    public User updateuserById(String userId , User u) {
        userrepo.findById(userId)
                                .map(existingUsr -> {
                                    existingUsr.setName(u.getName());
                                    existingUsr.setEmail(u.getEmail());
                                    existingUsr.setAbout(u.getAbout());
                                    return userrepo.save(existingUsr);
                                }).orElseThrow(()->new ResourceNotFoundException("enter usrer id is not found in server , id is "+ userId));
                                
                                return u;
                                        
    }

}

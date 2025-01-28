package com.user.service.UserService.controller;

import java.util.List;
// import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.UserService.entities.User;
import com.user.service.UserService.service.userService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
// import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
// import io.github.resilience4j.retry.annotation.Retry;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private userService userservice ;

    private org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

    //to create user 
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
            userservice.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }

    //to get all user
    @GetMapping
    public ResponseEntity<List<User>> getAllUsr() {
        List<User> li = userservice.getAllUser();
        return ResponseEntity.ok(li);
    }
    

   

    // to get single user
    @GetMapping("/{userId}")
    // @CircuitBreaker(name = "HotelRatingcb" , fallbackMethod = "hotelRatingFallbackMethod")
    // @Retry(name = "HotelRatingRetry" , fallbackMethod = "hotelRatingFallbackMethod")
    @RateLimiter(name = "userRateLimitor" , fallbackMethod = "hotelRatingFallbackMethod")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        logger.info("retry count");
        User user =userservice.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    // create a fallback method in case of any service is down this method is called 
    public ResponseEntity<User> hotelRatingFallbackMethod(String userId , Exception ex){
        
      User user = new User();
                user.setUserId("1245");
                user.setName("dummy user");
                user.setEmail("dummy@gmail.com");
                user.setAbout("this is dummy user");

                return new ResponseEntity<>(user , HttpStatus.OK);
    /*
     *  User user =  User.builder()
                    .userId("1245")
                    .name("dummy user")
                    .email("dummy@gmail.com")
                    .about("this is a dummy user")
                    .ratings(null)
                    .build();
     */
       
    }
    
    //to deletee user by id 
    @DeleteMapping("/{userId}")
    public ResponseEntity<List<User>> deleteById(@PathVariable String userId){
            userservice.deleteUserById(userId);
            return getAllUsr();

    } 

    // to update user by id 
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserById(@PathVariable String userId, @RequestBody User user) {
       User u1 = userservice.updateuserById(userId, user);  
        return ResponseEntity.ok(u1);
    }

}

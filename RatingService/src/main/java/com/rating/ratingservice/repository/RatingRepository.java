package com.rating.ratingservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rating.ratingservice.entity.Rating;
@Repository
public interface RatingRepository extends JpaRepository<Rating , String>{

    // custom finder method
    // to  find rating by userid
     List<Rating> findByUserId(String userId);
     

    // to dind rating by hotelid
     List<Rating> findByHotelId(String hotelId);

    

}

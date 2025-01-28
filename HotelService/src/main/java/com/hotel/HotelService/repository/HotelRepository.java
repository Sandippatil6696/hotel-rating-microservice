package com.hotel.HotelService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.HotelService.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel , String>{

}

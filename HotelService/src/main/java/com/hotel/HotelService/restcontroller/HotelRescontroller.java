package com.hotel.HotelService.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.HotelService.entity.Hotel;
import com.hotel.HotelService.service.HotelService;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/hotel")
public class HotelRescontroller {

    @Autowired
    private HotelService hotelservice;

    // create hotel
    @PostMapping
    public ResponseEntity<Hotel> createHot(@RequestBody Hotel h){
            hotelservice.createHotel(h);
            return ResponseEntity.status(HttpStatus.CREATED).body(h);

    }

    // get all hotel
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHot(){
    //    List<Hotel> hotel = hotelservice.getAllHotel();
        return ResponseEntity.ok(hotelservice.getAllHotel());
    }

    // get hotel by id
    @GetMapping("/{HotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String HotelId) {
        return ResponseEntity.ok(hotelservice.getHotelbyId(HotelId));
    }
    
    // delete hote by id 
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Hotel>> deleteHotById(@PathVariable String id){
        return ResponseEntity.ok(hotelservice.deleteHotelById(id));
    }

    // update hotel by id
    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updatehotById(@PathVariable String id, @RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelservice.updateHotelById(id, hotel));
    }

}

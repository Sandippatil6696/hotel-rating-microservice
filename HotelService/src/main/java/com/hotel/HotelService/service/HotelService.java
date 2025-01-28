package com.hotel.HotelService.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.HotelService.entity.Hotel;
import com.hotel.HotelService.exception.ResourceNotFoundException;
import com.hotel.HotelService.repository.HotelRepository;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelrepository;


    // create hotel
    public Hotel createHotel(Hotel h){
        String id =UUID.randomUUID().toString();
        h.setHotelId(id);
        return hotelrepository.save(h);
    }

    // get all 
    public List<Hotel> getAllHotel(){
        return hotelrepository.findAll();
    }

    // get hotel by id 
    public Hotel getHotelbyId(String id){
        return hotelrepository.findById(id)
                                .orElseThrow(()-> new ResourceNotFoundException("enter usrer id is not found in server , id is"+id));
    }

    //delete hotel by id
    public List<Hotel> deleteHotelById(String id){
        hotelrepository.deleteById(id);
        return getAllHotel();
    }

    // update hotel by id 
    public Hotel updateHotelById(String id , Hotel h){
        hotelrepository.findById(id)
                        .map(htl -> {
                            htl.setName(h.getName());
                            htl.setLocation(h.getLocation());
                            htl.setAbout(h.getAbout());
                            return hotelrepository.save(htl) ;
                        }).orElseThrow(()-> new ResourceNotFoundException("enter usrer id is not found in server , id is"+id));

        return h;
    }
}

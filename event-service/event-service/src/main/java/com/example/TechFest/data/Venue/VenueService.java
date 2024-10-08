package com.example.TechFest.data.Venue;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.TechFest.data.Event.Event;
import com.example.TechFest.data.Registration.RegRepo;
import com.example.TechFest.data.Registration.Registration;

@Service
public class VenueService {
	public VenueRepo venueRepo;

	
	@Autowired
	public VenueService(VenueRepo venueRepo ) {		
		this.venueRepo = venueRepo;
	}
	
	public List<Venue> getVenues(){
		return venueRepo.findAll();
	}
	
	public Venue addNewVenue( Venue venue){
		Optional<Venue> vOptional = venueRepo.findByVenueName(venue.venueName);


	      if(vOptional.isPresent()){
	          throw new IllegalStateException("venue is present");
	      }
	      venueRepo.save(venue);
	      return venue;
    }
	
	public void deleteReg(@PathVariable("RegID") Long id){
		boolean ev = venueRepo.existsById(id);
        if(!ev){
            throw new IllegalStateException(id + " does not exists");
        }
        venueRepo.deleteById(id);
    }
	public Venue updateVenue(Long Id,Venue venue){
		Venue venue2 = venueRepo.findById(Id).orElseThrow(()-> new IllegalStateException("does not exist"));
		
		venue2.setVenueName(venue.venueName);
		return venue2;
	}
}

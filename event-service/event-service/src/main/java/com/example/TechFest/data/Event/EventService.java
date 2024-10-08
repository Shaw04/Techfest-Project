package com.example.TechFest.data.Event;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TechFest.data.Sponser.Sponser;
import com.example.TechFest.data.Sponser.SponserRepo;
import com.example.TechFest.data.User1.User1;
import com.example.TechFest.data.User1.UserRepo;
import com.example.TechFest.data.Venue.Venue;
import com.example.TechFest.data.Venue.VenueRepo;

import jakarta.transaction.Transactional;





@Service

public class EventService {
	
	public final EventRepo eventRepo;
	public final VenueRepo venueRepo;
	public final UserRepo userRepo;
	public final SponserRepo sponserRepo;
	
	
	@Autowired
	public EventService(EventRepo eventRepo, VenueRepo venueRepo, UserRepo userRepo, SponserRepo sponserRepo) {
		super();
		this.eventRepo = eventRepo;
		this.venueRepo = venueRepo;
		this.userRepo = userRepo;
		this.sponserRepo = sponserRepo;
	}

	public List<Event> GetEvents(){
		
		return eventRepo.findAll();
		
	}
	
	public Event addNewEvent( Event event){
		
		Long venueId = event.getVenueId();
		Long userId = event.getUserId();
		Long sponserId = event.getSponserId();
		
		if (venueId != null) {
	        Optional<Venue> optionalVenue = venueRepo.findById(venueId);
	        
	        optionalVenue.ifPresent(event::setVenue);
	    }
		if (userId != null) {
	        Optional<User1> optionaluser = userRepo.findById(userId);
	        
	        optionaluser.ifPresent(event::setOragnizerID);
	    }
		if (sponserId != null) {
	        Optional<Sponser> optionalsponser = sponserRepo.findById(sponserId);
	        
	        optionalsponser.ifPresent(event::setSponsor);
	    }
      eventRepo.save(event);
      return event;
	}
	
	public void deleteEvent( Long id){
		boolean ev = eventRepo.existsById(id);
        if(!ev){
            throw new IllegalStateException(id + " does not exists");
        }
        eventRepo.deleteById(id);
    }
		@Transactional
	public Event updateEvent(Long id,Event event) {
		Event event2 = eventRepo.findById(id).orElseThrow(()-> new IllegalStateException("does not exist"));
		
		Long venueId = event.getVenueId();
		Long userId = event.getUserId();
		Long sponserId = event.getSponserId();
		
		if (venueId != null) {
	        Optional<Venue> optionalVenue = venueRepo.findById(venueId);
	        
	        optionalVenue.ifPresent(event2::setVenue);
	    }
		if (userId != null) {
	        Optional<User1> optionaluser = userRepo.findById(userId);
	        
	        optionaluser.ifPresent(event2::setOragnizerID);
	    }
		if (sponserId != null) {
	        Optional<Sponser> optionalsponser = sponserRepo.findById(sponserId);
	        
	        optionalsponser.ifPresent(event2::setSponsor);
	    }
		
		event2.update(event.Title,event.Description,event.date,event.Time,event.venueId,event.Logo);
		return event2;
	}
	
	

}

package com.example.TechFest.data.Registration;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.TechFest.data.Event.Event;
import com.example.TechFest.data.Event.EventRepo;
import com.example.TechFest.data.User1.User1;
import com.example.TechFest.data.User1.UserRepo;
import com.example.TechFest.data.Venue.Venue;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;



@Service

public class RegService {
	public RegRepo regRepo;
	public EventRepo eventRepo;
	public UserRepo userRepo;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	public RegService(RegRepo regRepo, EventRepo eventRepo, UserRepo userRepo) {
		super();
		this.regRepo = regRepo;
		this.eventRepo = eventRepo;
		this.userRepo = userRepo;
	}
	
	
	public List<Registration> GetReg(){
		return regRepo.findAll();
	}
	

	public Registration addNewReg(Registration registration){
		if (isUserRegisteredForEvent(registration.userId, registration.eventId)) {
			 throw new IllegalStateException("Reg is present");
        } 
		if (registration.eventId != null) {
	        Optional<Event> optionalVenue = eventRepo.findById(registration.eventId);
	        
	        optionalVenue.ifPresent(registration::setEvent);
	    }
		
	    regRepo.save(registration);
	    return registration;
    }
	public boolean isUserRegisteredForEvent(String userId, Long eventId) {
		Query query = entityManager.createQuery(
				"SELECT COUNT(r) FROM Registration r " +
				        "WHERE r.userId = :userId AND r.event.eventID = :eventId"
				);
		query.setParameter("userId", userId);
		query.setParameter("eventId", eventId);
		Long count = (Long) query.getSingleResult();
		return count > 0;
	}
	
	public void deleteReg(@PathVariable("RegID") Long id){
		boolean ev = regRepo.existsById(id);
        if(!ev){
            throw new IllegalStateException(id + " does not exists");
        }
        regRepo.deleteById(id);
    }


}

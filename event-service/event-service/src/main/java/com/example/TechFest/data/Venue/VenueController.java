package com.example.TechFest.data.Venue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TechFest.data.Event.Event;



@CrossOrigin
@RestController
@RequestMapping(path = "/venue")

public class VenueController {
	
	public VenueService venueService;

	
	@Autowired
	public VenueController(VenueService venueService) {	
		this.venueService = venueService;
	}
	
	@GetMapping
	public List<Venue> getVenues(){
		return venueService.getVenues();
	}
	
	@PostMapping
    public Venue addNewVenue(@RequestBody Venue venue){
        return venueService.addNewVenue(venue);
    }

    @DeleteMapping(path = "{VenueID}")
    public void deleteVenue(@PathVariable("VenueID") Long id){
        venueService.deleteReg(id);
    }
    @PutMapping(path = "{VenueID}")
    public Venue updateVenue(@PathVariable("VenueID")Long Id,
                           @RequestBody Venue venue){
        return venueService.updateVenue(Id,venue);
    }
}

package com.example.TechFest.data.Venue;


import java.util.*;

import com.example.TechFest.data.Event.Event;

import jakarta.persistence.*;

@Entity
@Table

public class Venue {
	@Id
	@SequenceGenerator(
            name = "venue_seq",
            sequenceName = "venue_seq",
            allocationSize = 1
    )
	@GeneratedValue(
            generator = "venue_seq",
            strategy = GenerationType.SEQUENCE
    )
	Long VenueID;
	String venueName;
	
	@OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    private List<Event> events;
	
	
	
	public Venue() {
	}
	public Venue(String venueName) {
		this.venueName = venueName;
	}
	public Long getVenueID() {
		return VenueID;
	}
	public void setVenueID(Long venueID) {
		this.VenueID = venueID;
	}
	public String getVenueName() {
		return venueName;
	}
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}
	
	

}

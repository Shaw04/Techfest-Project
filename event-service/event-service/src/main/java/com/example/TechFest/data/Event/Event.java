package com.example.TechFest.data.Event;



import java.util.*;


import com.example.TechFest.data.Sponser.Sponser;
import com.example.TechFest.data.User1.User1;
import com.example.TechFest.data.Venue.Venue;

import jakarta.persistence.*;


@Entity
@Table

public class Event {
	@Id
	@SequenceGenerator(
            name = "Event_Seq",
            sequenceName = "Event_Seq",
            allocationSize = 1
    )
	@GeneratedValue(
            generator = "Event_Seq",
            strategy = GenerationType.SEQUENCE
    )
	@Column(name = "eventid")
	Long eventID;
	@Column(name = "title")
	String Title;
	@Column(name = "description",length=1000)
	String Description;
	@Column(name = "date")
	String date;
	@Column(name = "time")
	String Time;
	@Column(name = "logo",length=1000)
	String Logo;
	
	
	
	
	
	public String getLogo() {
		return Logo;
	}


	public void setLogo(String logo) {
		this.Logo = logo;
	}


	@ManyToOne
	@JoinColumn(name = "venueid")
	private Venue venue;
	
	@Transient
	Long venueId;
	
	public Event(String title, String description, String date, String time,String logo) {
		this.Title = title;
		this.Description = description;
		this.date = date;
		this.Time = time;
		this.Logo = logo;
	}
	
	
	public Event(String title, String description, String date, String time, Long venueId, Long userId,
			Long sponserId) {
		this.Title = title;
		this.Description = description;
		this.date = date;
		this.Time = time;
		this.venueId = venueId;
		this.userId = userId;
		this.sponserId = sponserId;
	}
	
	public void update(String title, String description, String date, String time, Long venueId, 
			String Logo) {
		this.Title = title;
		this.Description = description;
		this.date = date;
		this.Time = time;
		this.venueId = venueId;
		this.Logo = Logo;
	}


	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userid")
	private User1 OragnizerID;
	
	@Transient
	Long userId;
	
	
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sponserid")
    private Sponser sponsor;
	
	@Transient
	Long sponserId;
	
	
	
	
	public Long getVenueId() {
		return venueId;
	}
	public void setVenueId(Long venueId) {
		this.venueId = venueId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getSponserId() {
		return sponserId;
	}
	public void setSponserId(Long sponserId) {
		this.sponserId = sponserId;
	}
	public Event() {

	}
	public Long getEventID() {
		return eventID;
	}
	public void setEventID(Long eventID) {
		this.eventID = eventID;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		this.Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		this.Description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		this.Time = time;
	}
	public Venue getVenue() {
		return venue;
	}
	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	public User1 getOragnizerID() {
		return OragnizerID;
	}
	public void setOragnizerID(User1 oragnizerID) {
		this.OragnizerID = oragnizerID;
	}
	public Sponser getSponsor() {
		return sponsor;
	}
	public void setSponsor(Sponser sponsor) {
		this.sponsor = sponsor;
	}
	
	
	
	
	
	
}

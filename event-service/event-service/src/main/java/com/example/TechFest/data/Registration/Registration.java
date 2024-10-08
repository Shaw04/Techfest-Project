package com.example.TechFest.data.Registration;

import com.example.TechFest.data.Event.Event;


import jakarta.persistence.*;

@Entity
@Table

public class Registration {
	@Id
	@SequenceGenerator(
            name = "reg_seq",
            sequenceName = "reg_seq",
            allocationSize = 1
    )
	@GeneratedValue(
            generator = "reg_seq",
            strategy = GenerationType.SEQUENCE
    )
	@Column(name = "regid")
	Long RegID;
	
	@Column(name = "regdate")
	String RegDate;
	
	@ManyToOne
    @JoinColumn(name = "eventid")
    private Event event;
	
	@Transient
	Long eventId;
	

	
	
	
	@Column(name = "user_id")
	 String userId;
	
	
	public Long getRegID() {
		return RegID;
	}
	public void setRegID(Long regID) {
		this.RegID = regID;
	}
	
	public String getRegDate() {
		return RegDate;
	}
	public void setRegDate(String regDate) {
		this.RegDate = regDate;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}

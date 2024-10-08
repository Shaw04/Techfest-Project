package com.example.TechFest.data.Sponser;

import java.util.*;

import com.example.TechFest.data.Event.Event;

import jakarta.persistence.*;


@Entity
@Table
public class Sponser {
	@Id
	@SequenceGenerator(
            name = "sponser_seq",
            sequenceName = "sponser_seq",
            allocationSize = 1
    )
	@GeneratedValue(
            generator = "sponser_seq",
            strategy = GenerationType.SEQUENCE
    )
	@Column(name = "sponserid")
	Long SponserID;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "logo")
	String Logo;
	
	@Column(name = "website")
	String Website;
	
	@Column(name = "contactperson")
	String ContactPerson;
	
	@Column(name = "contactemail")
	String ContactEmail; 
	
	@Column(name = "contactnumber")
	String ContactNumber;
	
	
	
	
	public Sponser(String name, String logo, String website, String contactPerson, String contactEmail,
			String contactNumber) {
		this.name = name;
		this.Logo = logo;
		this.Website = website;
		this.ContactPerson = contactPerson;
		this.ContactEmail = contactEmail;
		this.ContactNumber = contactNumber;
	}
	public void update(String name, String logo, String website, String contactPerson, String contactEmail,
			String contactNumber) {
		this.name = name;
		this.Logo = logo;
		this.Website = website;
		this.ContactPerson = contactPerson;
		this.ContactEmail = contactEmail;
		this.ContactNumber = contactNumber;
	}
	
	public Long getSponserID() {
		return SponserID;
	}
	public void setSponserID(Long sponserID) {
		this.SponserID = sponserID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogo() {
		return Logo;
	}
	public void setLogo(String logo) {
		this.Logo = logo;
	}
	public String getWebsite() {
		return Website;
	}
	public void setWebsite(String website) {
		this.Website = website;
	}
	public String getContactPerson() {
		return ContactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.ContactPerson = contactPerson;
	}
	public String getContactEmail() {
		return ContactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.ContactEmail = contactEmail;
	}
	public String getContactNumber() {
		return ContactNumber;
	}
	public Sponser() {
		
	}
	public void setContactNumber(String contactNumber) {
		this.ContactNumber = contactNumber;
	}
	

}

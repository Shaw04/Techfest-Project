package com.example.TechFest.data.Venue;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VenueConfig {
	
	@Bean
	CommandLineRunner venueRunner(VenueRepo venueRepo) {
		return args->{
			if(venueRepo.count()==0) {
			Venue Auditorium = new Venue("Auditorium");
			Venue CHall = new Venue("C-Hall");
			venueRepo.saveAll(
					List.of(Auditorium,CHall));
			}
		};
	}

}

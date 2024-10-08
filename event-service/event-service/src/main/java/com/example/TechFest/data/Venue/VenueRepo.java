package com.example.TechFest.data.Venue;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TechFest.data.Sponser.Sponser;

@Repository
public interface VenueRepo extends JpaRepository<Venue, Long> {
	Optional<Venue> findByVenueName(String VenueName);
}

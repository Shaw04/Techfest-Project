package com.example.TechFest.data.Registration;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RegRepo extends JpaRepository<Registration, Long> {
//	Optional<Registration> findByUserIdAndEventID(Long userId, Long eventId);
}

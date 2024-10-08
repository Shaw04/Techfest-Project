package com.example.TechFest.data.Sponser;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SponserRepo extends JpaRepository<Sponser, Long>{

	Optional<Sponser> findByName(String Name);

}

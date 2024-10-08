package com.example.TechFest.data.Sponser;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.TechFest.data.Venue.Venue;



@Service
public class SponserService {
	
	public SponserRepo sponserRepo;
	
	
	@Autowired
	public SponserService(SponserRepo sponserRepo) {
	 	
		this.sponserRepo = sponserRepo;
	}
	
	
	public List<Sponser> GetSponser(){
		return sponserRepo.findAll();
	}
//	public boolean isSponsorNameExists(String sponsorName) {
//        return sponserRepo.existsByName(sponsorName);
//    }
	
	public Sponser addNewSponser(@RequestBody Sponser sponser){
		
		Optional<Sponser> sponsorOptional = sponserRepo.findByName(sponser.name);

	      if(sponsorOptional.isPresent()){
	          throw new IllegalStateException("sponser is present");
	      }
	      sponserRepo.save(sponser);
	      return sponser;
    }
	
	public void deletesponser(@PathVariable("SponserID") Long id){
		boolean ev = sponserRepo.existsById(id);
        if(!ev){
            throw new IllegalStateException(id + " does not exists");
        }
        sponserRepo.deleteById(id);
    }
	public Sponser updateSponser(Long Id,Sponser sponser){
		Sponser sponser2 = sponserRepo.findById(Id).orElseThrow(()-> new IllegalStateException("does not exist"));
		sponser2.update(sponser.name,sponser.Logo,sponser.Website,sponser.ContactPerson,sponser.ContactEmail,sponser.ContactNumber);
		
		return sponser2;
	}
}

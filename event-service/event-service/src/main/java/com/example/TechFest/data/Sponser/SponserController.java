package com.example.TechFest.data.Sponser;

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

import com.example.TechFest.data.Venue.Venue;



@CrossOrigin
@RestController
@RequestMapping(path = "/sponser")
public class SponserController {
	
	public SponserService sponserService;

	
	@Autowired
	public SponserController(SponserService sponserService) {
	 	
		this.sponserService = sponserService;
	}
	
	@GetMapping
	public List<Sponser> GetSponser(){
		return sponserService.GetSponser();
	}
	
	@PostMapping
    public Sponser addNewSponser(@RequestBody Sponser sponser){
        return sponserService.addNewSponser(sponser);
    }

    @DeleteMapping(path = "{SponserID}")
    public void deletesponser(@PathVariable("SponserID") Long id){
        sponserService.deletesponser(id);
    }
    @PutMapping(path = "{SponserID}")
    public Sponser updateSponser(@PathVariable("SponserID")Long Id,
                           @RequestBody Sponser sponser){
        return sponserService.updateSponser(Id,sponser);
    }

}

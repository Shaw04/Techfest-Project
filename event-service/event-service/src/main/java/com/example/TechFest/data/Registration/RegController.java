package com.example.TechFest.data.Registration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TechFest.data.User1.User1;



@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(path = "/reg")
public class RegController {
	public RegService regService;

	
	@Autowired
	public RegController(RegService regService) {	
		this.regService = regService;
	}
	
	@GetMapping
	public List<Registration> GetReg(){
		return regService.GetReg();
	}
	




	
	@GetMapping("/status")
	public ResponseEntity<Boolean> checkRegistrationStatus(@RequestParam("userId") String userId, @RequestParam("eventId") Long eventId){
		System.out.println(userId+" "+ eventId);
		boolean isRegistered = regService.isUserRegisteredForEvent(userId, eventId);
		
        return ResponseEntity.ok(isRegistered);
	}
	
	@PostMapping
    public Registration addNewReg(@RequestBody Registration registration){
        return regService.addNewReg(registration);
    }

    @DeleteMapping(path = "{RegID}")
    public void deleteReg(@PathVariable("RegID") Long id){
        regService.deleteReg(id);
    }
//    public Registration updateReg(@PathVariable("RegID")Long Id,
//            @RequestBody Registration registration){
//    		return regService.updateReg(Id,registration);
//    }

}

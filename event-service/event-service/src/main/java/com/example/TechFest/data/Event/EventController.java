package com.example.TechFest.data.Event;

import java.util.*;

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




@CrossOrigin
@RestController
@RequestMapping(path = "/event")

public class EventController {
	
	public EventService eventService;
	
	
	@Autowired
	public EventController(EventService eventService) {
		this.eventService = eventService;
	}



	@GetMapping
	public List<Event> GetEvents(){
		System.out.println("you called here");
		return eventService.GetEvents();
	}
	
	@PostMapping
    public Event addNewEvent(@RequestBody Event event){
        return eventService.addNewEvent(event);
    }

    @DeleteMapping(path = "{eventID}")
    public void deleteEvent(@PathVariable("eventID") Long id){
        eventService.deleteEvent(id);
    }
    
    @PutMapping(path = "{eventID}")
    public Event updateEvent(@PathVariable("eventID")Long Id,
                           @RequestBody Event event){
        return eventService.updateEvent(Id,event);
    }
}

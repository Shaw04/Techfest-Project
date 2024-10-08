package com.example.TechFest.data.User1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.TechFest.data.Event.Event;



@Service
public class UserService {
	public final UserRepo userRepo;
	
	@Autowired
	public UserService(UserRepo userRepo)  {
		this.userRepo = userRepo;
	}
	
	public List<User1> GetUsers(){
		return userRepo.findAll();
	}
	
	public User1 addNewUser( User1 user){
		Optional<User1> user1 = userRepo.findByUsername(user.username);


      if(user1.isPresent()){
          throw new IllegalStateException("User is present");
      }
      userRepo.save(user);
      return user;
	}
	
	public User1 authenticate(User1 credentials) {
		Optional<User1> user1 = userRepo.findByUsername(credentials.username);
		if(user1.isPresent()) {
			User1 user = user1.get();
			 if (user.getPassword().equals(credentials.Password)) {
	                return user;
	          }
		}
		return null;
	}
	
	public void deleteUser( Long id){
		boolean ev = userRepo.existsById(id);
        if(!ev){
            throw new IllegalStateException(id + " does not exists");
        }
        userRepo.deleteById(id);
    }	
	public User1 updateUser(Long Id,User1 user1){
		User1 user12 = userRepo.findById(Id).orElseThrow(()-> new IllegalStateException("does not exist"));
		user12.update(user1.username, user1.Password, user1.Email,user1.Role);
		return user12;
	}
}

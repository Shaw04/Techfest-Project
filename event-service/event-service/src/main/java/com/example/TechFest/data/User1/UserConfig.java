package com.example.TechFest.data.User1;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
	
	@Bean
	CommandLineRunner useRunner(UserRepo userRepo) {
			return args->{
				if(userRepo.count()==0) {
				User1 jack = new User1("Jack", "Jack123", "Jack123@gmail.com", "Participant");
				User1 Drake = new User1("Drake", "Drake1234", "Drake123@gmail.com", "Paricipant");
				User1 David = new User1("David", "David123", "David123@gmail.com", "Organizer");
				userRepo.saveAll(List.of(jack,Drake,David));
				}
			};
		
	}
}

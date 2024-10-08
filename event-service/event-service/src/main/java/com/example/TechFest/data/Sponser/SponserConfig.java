package com.example.TechFest.data.Sponser;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SponserConfig {
	
	@Bean
	CommandLineRunner sponseRunner(SponserRepo sponserRepo) {
		return args->{
			if(sponserRepo.count()==0) {
			Sponser Redbull = new Sponser("Redbull", "img src", "https://www.redbull.com", "Hitesh", "redbullORG@gmail.com", "7894561230");
			Sponser Lays = new Sponser("Lays", "img src", "https://www.Lays.com", "Lucky", "LaysORG@gmail.com", "9638527410");
			sponserRepo.saveAll(
					List.of(Redbull,Lays));
			}
		};
	}
}

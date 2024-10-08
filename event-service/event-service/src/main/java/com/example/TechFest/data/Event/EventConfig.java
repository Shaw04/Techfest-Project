package com.example.TechFest.data.Event;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfig {
	@Bean
	CommandLineRunner commandLineRunner(EventRepo eventRepo) {
		return args->{
			if(eventRepo.count()==0) {
			Event dance = new Event("Dance india dance","The college techfest dance event is a vibrant and eagerly anticipated competition that showcases the dancing prowess of students from various departments. Set within the bustling energy of the techfest, this event invites participants to express their creativity and passion through dance, covering styles ranging from hip-hop and contemporary to classical and folk. It serves as a platform for students to unleash their artistic side amidst the tech-centric activities, fostering a spirited cultural exchange. The event not only highlights individual talent but also emphasizes teamwork through group performances. Judges typically include esteemed professionals from the dance community who provide valuable feedback and select the winners.The dance event is a highlight of the techfest, offering a lively break from technical pursuits and allowing students to celebrate their cultural heritage and innovation in dance.","04/02/2024","4:00 PM","src\\assets\\dance_photo.jpg");
			Event singing = new Event("Sa re ga ma","The college techfest singing event is a captivating showcase that highlights the vocal talents of students across diverse musical genres. As a centerpiece of the festival's cultural lineup, this competition invites aspiring singers to step into the spotlight and share their unique voices. Participants have the opportunity to perform solo or in groups, interpreting songs ranging from classical melodies to contemporary hits. Esteemed judges from the music industry critique the performances, providing insightful feedback and encouragement. This event not only nurtures budding musical talents but also enriches the techfest experience, creating a melodious harmony between technology and the arts. It's a chance for students to gain exposure, experience live performance pressure, and revel in their shared love for music.","04/02/2024","6:00 PM","src\\assets\\singing_photo.jpg");
			Event LanWar = new Event("LaN WaR","The LAN Gaming event at the college techfest is an electrifying competition that draws gamers from all corners of the campus. This event features popular multiplayer games where participants battle it out in a high-speed network environment, showcasing their skills in strategy, teamwork, and quick reflexes. Gamers compete in a series of matches across different genres, including first-person shooters, real-time strategy games, and MOBAs. The atmosphere is charged with excitement as players navigate digital battlefields, strategize with teammates, and vie for the title of campus champion. With state-of-the-art gaming setups and live screenings, this event is a highlight for gaming enthusiasts, offering not just a competition but a celebration of the gaming culture.","04/02/2024","6:00 PM","src\\assets\\gaming_photo.webp");
			eventRepo.saveAll(List.of(dance,singing,LanWar));
			}
		};
	}

}

package com.example.springboot;

import com.example.springboot.models.Player;
import com.example.springboot.models.Tournament;
import com.example.springboot.repositories.PlayerRepo;
import com.example.springboot.repositories.TournamentRepo;
import com.example.springboot.services.TournamentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {SpringApplication.run(SpringbootApplication.class, args);}

	@Autowired
	private PlayerRepo playerRepo;
	@Autowired
	private TournamentRepo tournamentRepo;

	@Autowired
	private TournamentServiceImpl tournamentServiceImpl;


	@Bean
	public CommandLineRunner initData() {
		return (args) -> {
			//SAVE TOURNAMENT AND PLAYERS FOR TESTING PURPOSES
			//RELOAD SERVER AFTER CHANGES
			Tournament tournament = new Tournament(1l,"Roland Garros",5);
			Player player1 = new Player(1l,"Roger Federer",50);
			Player player2= new Player(2l,"Rafael Nadal",50);


			player1.setTournament(tournament);
			player2.setTournament(tournament);


			tournamentRepo.save(tournament);
			playerRepo.save(player1);
			playerRepo.save(player2);


		};
	}

}

package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository repositoryPlayer, GameRepository repositoryGame, GamePlayerRepository repositoryGamePlayer, ShipRepository repositoryShip, SalvoRepository repositorySalvo) {
		return (args) -> {
			// save a couple of customers
			Player player1 = new Player("Jack", "12345");
			Player player2 = new Player("Chloe", "qwert");
			Player player3 = new Player("Hans", "qwert");
			repositoryPlayer.save(player1);
			repositoryPlayer.save(player2);
			repositoryPlayer.save(player3);

			Date firstDate = new Date();
			Game game1= new Game(firstDate);
			Date newDate = Date.from(firstDate.toInstant().plusSeconds(3600));
			Game game2= new Game(newDate);
			Game game3 = new Game(firstDate);
			repositoryGame.save(game1);
			repositoryGame.save(game2);
			repositoryGame.save(game3);

			//create GamePlayer
			GamePlayer gameplayer1 = new GamePlayer(game1, player1, firstDate);
			GamePlayer gameplayer2 = new GamePlayer(game1, player2, firstDate);
			GamePlayer gameplayer3 = new GamePlayer(game2, player3, firstDate);
			GamePlayer gameplayer4 = new GamePlayer(game3, player1, firstDate);
			repositoryGamePlayer.save(gameplayer1);
			repositoryGamePlayer.save(gameplayer2);
			repositoryGamePlayer.save(gameplayer3);
			repositoryGamePlayer.save(gameplayer4);

			//create new ships
			List<String> list1= new ArrayList<String>(3);
			list1.add("A1");
			list1.add("A2");
			list1.add("A3");
			Ship ship1 = new Ship("yacht", list1, gameplayer1 );
			Ship ship2 = new Ship("destroyer", list1, gameplayer1);
			Ship ship3 = new Ship("flugzeugträger", list1, gameplayer3);
			repositoryShip.save(ship1);
			repositoryShip.save(ship2);
			repositoryShip.save(ship3);

			List<String> listSalvo1 = new ArrayList<String>(3);
			listSalvo1.add("A1");
			listSalvo1.add("A6");
			listSalvo1.add("A7");

			Salvo salvo1 = new Salvo( listSalvo1, gameplayer1, 1);
			Salvo salvo2 = new Salvo( listSalvo1, gameplayer2, 2);
			Salvo salvo3 = new Salvo( listSalvo1, gameplayer3, 3);

			repositorySalvo.save(salvo1);
			repositorySalvo.save(salvo2);
			repositorySalvo.save(salvo3);
		};
	}
}

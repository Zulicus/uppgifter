package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RPSController {
	// Code for the Muliplayer functionality
	@RequestMapping(value = "/game/multiplayer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String multiplayer(String playerOneInput, String playerTwoInput) {
		RPSbean bean = new RPSbean(playerOneInput, playerTwoInput);
		// JSON-pattern for PvP
		String pattern = "{\"Player Once Chose\":\"%s\",\"Player Two Chose\":\"%s\",\"Result\":\"%s\"}";
		bean.whoWon();
		return String.format(pattern, bean.getPlayerOneInput(), bean.getPlayerTwoInput(), bean.getVictor());

	}

	// Code for the Singleplayer functionality
	@RequestMapping(value = "/game/singleplayer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String singleplayer(String playerInput) {
		SoloRPSbean bean = new SoloRPSbean(playerInput);
		// JSON-pattern for vs AI
		String pattern = "{\"The Player Chose\":\"%s\",\"The Computer Chose\":\"%s\",\"Result\":\"%s\"}";
		bean.whoWon();
		return String.format(pattern, bean.getPlayerInput(), bean.getComputerInput(), bean.getVictor());

	}

	// Code to show the current score
	@RequestMapping(value = "/game/score", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String score() {
		// JSON-pattern for the score
		String pattern = "{\"Player vs Player\":{\"Ties\":\"%s\",\"Player One\":{\"Wins\":\"%s\"},\"Player Two\":{\"Wins\":\"%s\"}},\"Single Player\":{\"Ties\":\"%s\",\"Player\":{\"Wins\":\"%s\"},\"Computer\":{\"Wins\":\"%s\"}}}";
		return String.format(pattern, Score.MULTIPLAYERTIES, Score.PLAYE1RWINS, Score.PLAYER2WINS,
				Score.SINGLEPLAYERTIES, Score.PLAYERWINS, Score.COMPUTERWINS);

	}
	
	// Code to show the current score
		@RequestMapping(value = "/game/score/set", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
		public String setScore(int multities,int p1wins,int p2wins,int singleties,int playerwins,int compwins) {
			Score.MULTIPLAYERTIES=multities;
			Score.PLAYE1RWINS=p1wins;
			Score.PLAYER2WINS=p2wins;
			Score.SINGLEPLAYERTIES=singleties;
			Score.PLAYERWINS=playerwins;
			Score.COMPUTERWINS=compwins;
			return score();

		}
		
		// Code to show the current score
		@RequestMapping(value = "/game/score/reset", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
		public String resetScore() {
			Score.MULTIPLAYERTIES=0;
			Score.PLAYE1RWINS=0;
			Score.PLAYER2WINS=0;
			Score.SINGLEPLAYERTIES=0;
			Score.PLAYERWINS=0;
			Score.COMPUTERWINS=0;
		return score();

		}
}

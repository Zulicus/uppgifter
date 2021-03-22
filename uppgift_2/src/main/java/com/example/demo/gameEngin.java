package com.example.demo;

//Where the game logic is
public class gameEngin {
	public static String run(String playerOneInput, String playerTwoInput) {
		switch (playerOneInput) {
		case "rock":
			switch (playerTwoInput) {
			case "rock":
				return "Tie";
			case "paper":
				return "Player Two Won!";
			case "scissor":
				return "Player One Won!";
			default:
				return "Invalid Input by player two";
			}
		case "paper":
			switch (playerTwoInput) {
			case "rock":
				return "Player One Won!";
			case "paper":
				return "Tie";
			case "scissor":
				return "Player Two Won!";
			default:
				return "Invalid Input by player two";
			}
		case "scissor":
			switch (playerTwoInput) {
			case "rock":
				return "Player Two Won!";
			case "paper":
				return "Player One Won!";
			case "scissor":
				return "Tie";
			default:
				return "Invalid Input by player two";
			}
		default:
			return "Invalid Input by player one";
		}

	}
}

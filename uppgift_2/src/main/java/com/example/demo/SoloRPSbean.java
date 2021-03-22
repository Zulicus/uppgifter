package com.example.demo;

import java.util.Random;

public class SoloRPSbean {

	private String playerInput;
	private String computerInput;
	private String victor;

	public SoloRPSbean(String playerInput) {
		this.playerInput = playerInput;
	}

	public void whoWon() {
		String computerInput;
		Random rand = new Random();
		// Where the computer chooses rock paper or scissor
		switch (rand.nextInt(3)) {
		case 0:
			computerInput = "rock";
			break;
		case 1:
			computerInput = "paper";
			break;
		case 2:
			computerInput = "scissor";
			break;
		default:
			computerInput = "error";
		}
		this.computerInput = computerInput;
		this.victor = gameEngin.run(playerInput, computerInput);
		// Score-tracking
		if (gameEngin.run(playerInput, computerInput).equals("Player One Won!")) {
			Score.PLAYERWINS++;
		} else if (gameEngin.run(playerInput, computerInput).equals("Player Two Won!")) {
			Score.COMPUTERWINS++;
		} else if (gameEngin.run(playerInput, computerInput).equals("Tie")) {
			Score.SINGLEPLAYERTIES++;
		}
	}

	public String getComputerInput() {
		return computerInput;
	}

	public String getPlayerInput() {
		return playerInput;
	}

	public String getVictor() {
		return victor;
	}
}

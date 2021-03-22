package com.example.demo;

public class RPSbean {
	private String playerOneInput;
	private String playerTwoInput;
	private String victor;

	public RPSbean(String playerOneInput, String playerTwoInput) {
		this.playerOneInput = playerOneInput;
		this.playerTwoInput = playerTwoInput;
	}

	public void whoWon() {
		this.victor = gameEngin.run(playerOneInput, playerTwoInput);
		// Score-tracking
		if (gameEngin.run(playerOneInput, playerTwoInput).equals("Player One Won!")) {
			Score.PLAYE1RWINS++;
		} else if (gameEngin.run(playerOneInput, playerTwoInput).equals("Player Two Won!")) {
			Score.PLAYER2WINS++;
		} else if (gameEngin.run(playerOneInput, playerTwoInput).equals("Tie")) {
			Score.MULTIPLAYERTIES++;
		}
	}

	public String getPlayerOneInput() {
		return playerOneInput;
	}

	public String getPlayerTwoInput() {
		return playerTwoInput;
	}

	public String getVictor() {
		return victor;
	}
}

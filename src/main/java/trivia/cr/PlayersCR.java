package trivia.cr;

import java.util.ArrayList;

public class PlayersCR {

	private ArrayList<PlayerCR> playerCRS;
	private int currentPlayerIndex;

	public PlayersCR() {
		this.playerCRS = new ArrayList<>();
		this.currentPlayerIndex = 0;
	}

	public PlayerCR getCurrentPlayer() {
		return playerCRS.get(currentPlayerIndex);
	}

	public String getCurrentPlayerName() {
		return getCurrentPlayer().getName();
	}

	public int howManyPlayers() {
		return playerCRS.size();
	}

	public boolean isPlayerNameValid(String name) {
		for (PlayerCR playerCR : playerCRS) {
			if (playerCR.getName().equals(name))
				return false;
		}
		return true;
	}

	public void addPlayer(String playerName) {
		playerCRS.add(new PlayerCR(playerName));
	}

	public void changePlayersTurn() {
		currentPlayerIndex++;

		if (currentPlayerIndex == howManyPlayers()) {
			currentPlayerIndex = 0;
		}
	}

	public void updatePlayerPosition(int roll) {
		getCurrentPlayer().updatePosition(roll);
	}

}

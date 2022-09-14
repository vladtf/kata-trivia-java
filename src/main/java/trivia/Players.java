package trivia;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private int currentIndex = 0;

    private List<Player> players = new ArrayList<>();

    public Players() {
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    private Player getCurrentPlayer() {
        return players.get(currentIndex);
    }

    public void moveToNextPlayer() {
        currentIndex++;
        if (currentIndex == players.size()) currentIndex = 0;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public boolean didCurrentPlayerWin() {
        return getCurrentPlayer().didPlayerWin();
    }

    public String getCurrentPlayerName() {
        return getCurrentPlayer().getName();
    }

    public void incrementCurrentPlayerPurse() {
        getCurrentPlayer().incrementPurse();
        System.out.println(getCurrentPlayerName() + " now has " + getCurrentPlayer().getPurse() + " Gold Coins.");

    }

    public void moveCurrentPlayerInPenaltyBox() {
        getCurrentPlayer().moveInPenaltyBox();
    }

    public void moveCurrentPlayerOutOfPenaltyBox() {
        getCurrentPlayer().moveOutOfPenaltyBox();
    }

    public boolean isCurrentPlayerInPenaltyBox() {
        return getCurrentPlayer().isInPenaltyBox();
    }

    public boolean isCurrentPlayerNotInPenaltyBox() {
        return !isCurrentPlayerInPenaltyBox();
    }

    public void moveCurrentPlayerToNextPlace(int roll) {
        getCurrentPlayer().moveToNextPlace(roll);
    }

    public void confirmGettingOutOfPenaltyBox() {
        getCurrentPlayer().setGettingOutOfPenaltyBox(true);
    }


    public void abortGettingOutOfPenaltyBox() {
        getCurrentPlayer().setGettingOutOfPenaltyBox(false);
    }

    public int getCurrentPlayerPlace() {
        return getCurrentPlayer().getPlace();
    }

    public boolean isCurrentPlayerAbleToMove() {
        return isCurrentPlayerNotInPenaltyBox() || getCurrentPlayer().isGettingOutOfPenaltyBox();
    }


}

package trivia;

import java.util.LinkedList;
import java.util.List;

import static trivia.Category.*;


// TODO refactor me
public class Game implements IGame {
    Players players = new Players();
    List<String> scienceQuestions = new LinkedList<>();
    List<String> popQuestions = new LinkedList<>();
    List<String> sportsQuestions = new LinkedList<>();
    List<String> rockQuestions = new LinkedList<>();

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.add(Pop.buildQuestion(i));
            scienceQuestions.add(Science.buildQuestion(i));
            sportsQuestions.add(Sports.buildQuestion(i));
            rockQuestions.add(Rock.buildQuestion(i));
        }
    }


    public boolean add(String playerName) {
        Player newPlayer = new Player(playerName, false, 0, 0, false);
        players.addPlayer(newPlayer);
        System.out.println(newPlayer + " was added");
        System.out.println("They are player number " + players.howManyPlayers());
        return true;
    }


    public void roll(int roll) {
        System.out.println(players.getCurrentPlayerName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (players.isCurrentPlayerNotInPenaltyBox()) {
            players.moveCurrentPlayerToNextPlace(roll);
            System.out.println("The category is " + currentCategory());
            askQuestion();
            return;
        }

        if (roll % 2 == 0) {
            System.out.println(players.getCurrentPlayerName() + " is not getting out of the penalty box");
            players.abortGettingOutOfPenaltyBox();
            return;
        }

        players.confirmGettingOutOfPenaltyBox();

        System.out.println(players.getCurrentPlayerName() + " is getting out of the penalty box");
        players.moveCurrentPlayerToNextPlace(roll);

        System.out.println("The category is " + currentCategory());
        askQuestion();
        players.moveCurrentPlayerOutOfPenaltyBox();
    }

    private void askQuestion() {
        String question = getQuestion();
        System.out.println(question);
    }

    private String getQuestion() {
        return getQuestionsByCurrentCategory().remove(0);
    }

    private List<String> getQuestionsByCurrentCategory() {
        return switch (currentCategory()) {
            case Pop -> popQuestions;
            case Science -> scienceQuestions;
            case Sports -> sportsQuestions;
            case Rock -> rockQuestions;
        };
    }

    private Category currentCategory() {
        return Category.fromPlaceToCategory(players.getCurrentPlayerPlace());
    }

    public boolean wasCorrectlyAnswered() {
        boolean notWon = true;

        if (players.isCurrentPlayerAbleToMove()) {
            System.out.println("Answer was correct!!!!");
            players.incrementCurrentPlayerPurse();
            notWon = players.didCurrentPlayerNotWin();
        }

        players.moveToNextPlayer();
        return notWon;
    }


    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.getCurrentPlayerName() + " was sent to the penalty box");
        players.moveCurrentPlayerInPenaltyBox();
        players.moveToNextPlayer();
        return true;
    }


}

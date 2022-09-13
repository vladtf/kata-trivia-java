package trivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static trivia.Category.*;


// TODO refactor me
public class Game implements IGame {
    List<String> players = new ArrayList<>();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];
    List<String> scienceQuestions = new LinkedList<>();
    List<String> popQuestions = new LinkedList<>();
    List<String> sportsQuestions = new LinkedList<>();
    List<String> rockQuestions = new LinkedList<>();


    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.add(Pop.buildQuestion(i));
            scienceQuestions.add((Science.buildQuestion(i)));
            sportsQuestions.add((Sports.buildQuestion(i)));
            rockQuestions.add(Rock.buildQuestion(i));
        }
    }


    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public boolean add(String playerName) {
        players.add(playerName);
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (isCurrentPlayerOutOfPenaltyBox(currentPlayer)) {

            places[currentPlayer] = places[currentPlayer] + roll;
            if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

            System.out.println(players.get(currentPlayer) + "'s new location is " + places[currentPlayer]);
            System.out.println("The category is " + currentCategory());
            askQuestion();
            return;
        }

        if (roll % 2 == 0) {
            System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
            return;

        }

        isGettingOutOfPenaltyBox = true;

        System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
        places[currentPlayer] = places[currentPlayer] + roll;
        if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

        System.out.println(players.get(currentPlayer) + "'s new location is " + places[currentPlayer]);
        System.out.println("The category is " + currentCategory());
        askQuestion();
        inPenaltyBox[currentPlayer] = false;
    }

    private boolean isCurrentPlayerOutOfPenaltyBox(int currentPlayer) {
        return !inPenaltyBox[currentPlayer];
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
        return Category.fromPlaceToCategory(places[currentPlayer]);
    }

    public boolean wasCorrectlyAnswered() {
        if (isCurrentPlayerOutOfPenaltyBox(currentPlayer)) {

            System.out.println("Answer was correct!!!!");
            purses[currentPlayer]++;
            System.out.println(players.get(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");

            boolean winner = didPlayerWin();
            moveToNextPlayer();

            return winner;
        }

        if (isGettingOutOfPenaltyBox) {
            System.out.println("Answer was correct!!!!");
            purses[currentPlayer]++;
            System.out.println(players.get(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");

            boolean winner = didPlayerWin();
            moveToNextPlayer();

            return winner;
        }

        moveToNextPlayer();
        return true;
    }

    private void moveToNextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        moveToNextPlayer();
        return true;
    }


    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }
}

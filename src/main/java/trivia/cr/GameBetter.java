package trivia.cr;

import trivia.IGame;

import java.util.ArrayList;
import java.util.List;

public class GameBetter implements IGame, ConstantsCR {

	protected PlayersCR playersCR = new PlayersCR();
	protected List<SubjectCR> subjectsInGame = new ArrayList<SubjectCR>();

	public GameBetter() {
		createSubjects();
		for (int i = 0; i < NUMBER_OF_QUESTIONS_PER_SUBJECT; i++) {
			for (SubjectCR s : subjectsInGame) {
				s.addQuestion(i);
			}
		}
	}
	
	protected void createSubjects() {
		SubjectFactoryCR factory = new SubjectFactoryCR();
		subjectsInGame.add(factory.createSubject(POP));
		subjectsInGame.add(factory.createSubject(SCIENCE));
		subjectsInGame.add(factory.createSubject(SPORTS));
		subjectsInGame.add(factory.createSubject(ROCK));
	}

	public boolean add(String playerName) {
		playersCR.addPlayer(playerName);

		System.out.println(playerName + " was added");
		System.out.println("They are player number " + playersCR.howManyPlayers());
		return true;
	}

	public void roll(int roll) {
		System.out.println(playersCR.getCurrentPlayerName() + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (playersCR.getCurrentPlayer().isInPenaltyBox()) {

			if (roll % 2 != 0) {

				updatePenaltyBoxStatus(true);
				changePlayerPosition(roll);
				askQuestion();

				playersCR.getCurrentPlayer().setPenaltyBox(false);

			} else {
				updatePenaltyBoxStatus(false);
			}

		} else {
			changePlayerPosition(roll);
			askQuestion();
		}
	}

	protected void updatePenaltyBoxStatus(boolean value) {
		playersCR.getCurrentPlayer().setGettingOutOfPenaltyBox(value);

		if (playersCR.getCurrentPlayer().isGettingOutOfPenaltyBox()) {
			System.out.println(playersCR.getCurrentPlayerName() + " is getting out of the penalty box");
		} else {
			System.out.println(playersCR.getCurrentPlayerName() + " is not getting out of the penalty box");
		}
	}

	protected void changePlayerPosition(int roll) {
		playersCR.updatePlayerPosition(roll);

		System.out.println(
				playersCR.getCurrentPlayerName() + "'s new location is " + playersCR.getCurrentPlayer().getPosition());
		System.out.println("The category is " + getCurrentCategory());
	}

	protected void askQuestion() {
		for (SubjectCR s : subjectsInGame) {
			s.askQuestionAccordingToPosition(playersCR.getCurrentPlayer().getPosition());
		}
	}

	protected String getCurrentCategory() {
		for (SubjectCR s : subjectsInGame) {
			if (s.isPlaceFromSubject(playersCR.getCurrentPlayer().getPosition())) {
				return s.getName();
			}
		}
		return null;
	}

	public boolean wasCorrectlyAnswered() {
		if (playersCR.getCurrentPlayer().isInPenaltyBox()) {
			if (playersCR.getCurrentPlayer().isGettingOutOfPenaltyBox()) {
				return isWinner();
			} else {
				playersCR.changePlayersTurn();
				return true;
			}
		} else {
			return isWinner();
		}
	}

	protected boolean isWinner() {
		updatePurse();

		boolean winner = didPlayerWin();
		playersCR.changePlayersTurn();

		return winner;
	}

	protected void updatePurse() {
		System.out.println("Answer was correct!!!!");
		playersCR.getCurrentPlayer().updatePurse();
		System.out.println(
				playersCR.getCurrentPlayerName() + " now has " + playersCR.getCurrentPlayer().getPurse() + " Gold Coins.");
	}

	public boolean wrongAnswer() {
		System.out.println("Question was incorrectly answered");
		System.out.println(playersCR.getCurrentPlayerName() + " was sent to the penalty box");

		playersCR.getCurrentPlayer().setPenaltyBox(true);

		playersCR.changePlayersTurn();

		return true;
	}

	protected boolean didPlayerWin() {
		return !(playersCR.getCurrentPlayer().getPurse() == MAX_NUMBER_OF_COINS);
	}

}

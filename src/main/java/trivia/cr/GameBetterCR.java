package trivia.cr;


import trivia.GameBetter;
import trivia.SubjectFactory;

public class GameBetterCR extends GameBetter {

	@Override
	protected void createSubjects() {
		super.createSubjects();
		this.subjectsInGame.add(new SubjectFactory().createSubject(GEOGRAPHY));
	}

	public boolean isPlayable() {
		return (playersCR.howManyPlayers() >= MIN_NUMBER_OF_PLAYERS);
	}

	@Override
	public boolean add(String playerName) {

		if (playersCR.isPlayerNameValid(playerName)) {
			playersCR.addPlayer(playerName);
			System.out.println(playerName + " was added");
			System.out.println("They are player number " + playersCR.howManyPlayers());

			return true;
		}

		System.out.println("The player " + playerName + " already exists!");
		return false;

	}
	
	@Override
	public void roll(int roll) {
		if (playersCR.getCurrentPlayer().hadSecondChance()) {
			askQuestion();
		} else {
			super.roll(roll);
		}
	}

	@Override
	protected boolean isWinner() {
		manageStreak();
		updatePurse();

		boolean winner = didPlayerWin();
		playersCR.changePlayersTurn();

		return winner;
	}

	private void manageStreak() {
		playersCR.getCurrentPlayer().addOneConsecutiveCorrectAnswers();

		if (playersCR.getCurrentPlayer().getConsecutiveCorrectAnswers() == NUMBER_OF_QUESTIONS_FOR_STREAK) {
			playersCR.getCurrentPlayer().setOnStreak(true);
		}
	}

	@Override
	public boolean wrongAnswer() {

		if (!playersCR.getCurrentPlayer().isInPenaltyBox()) {

			System.out.println("Question was incorrectly answered");

			playersCR.getCurrentPlayer().resetNumberOfQuestionsForStreak();

			if (playersCR.getCurrentPlayer().isOnStreak()) {
				updateStreak();
			} else {
				manageSecondChanceOnWrongAnswer();
			}
		} else {
			System.out.println("Player is already in penalty box!");
			playersCR.changePlayersTurn();
		}

		return true;
	}

	private void updateStreak() {
		playersCR.getCurrentPlayer().setOnStreak(false);
		System.out.println(playersCR.getCurrentPlayerName() + " lost his streak");

		playersCR.changePlayersTurn();
	}

	private void manageSecondChanceOnWrongAnswer() {
		if (playersCR.getCurrentPlayer().hadSecondChance()) {
			playersCR.getCurrentPlayer().setPenaltyBox(true);
			System.out.println(playersCR.getCurrentPlayerName() + " was sent to the penalty box");
			playersCR.getCurrentPlayer().setSecondChance(false);

			playersCR.changePlayersTurn();
		} else {
			playersCR.getCurrentPlayer().setSecondChance(true);
		}
	}

	private void manageSecondChanceOnCorrectAnswer() {
		System.out.println(playersCR.getCurrentPlayerName() + " avoided penalty box");
		playersCR.getCurrentPlayer().setSecondChance(false);
		playersCR.changePlayersTurn();
	}

	@Override
	protected boolean didPlayerWin() {
		return !(playersCR.getCurrentPlayer().getPurse() >= MAX_NUMBER_OF_COINS * 2);
	}

	@Override
    public boolean wasCorrectlyAnswered() {
        if (playersCR.getCurrentPlayer().isInPenaltyBox() && !playersCR.getCurrentPlayer().isGettingOutOfPenaltyBox()) {
            playersCR.changePlayersTurn();
            return true;
        } else if (playersCR.getCurrentPlayer().hadSecondChance()) {
            manageSecondChanceOnCorrectAnswer();
        }

        return isWinner();
    }
}

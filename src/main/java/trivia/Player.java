package trivia;

public class Player {
    private final String name;
    private boolean inPenaltyBox;
    private int purse;
    private int place;
    private boolean isGettingOutOfPenaltyBox;

    public Player(String name, boolean inPenaltyBox, int purses, int place, boolean isGettingOutOfPenaltyBox) {
        this.name = name;
        this.inPenaltyBox = inPenaltyBox;
        this.purse = purses;
        this.place = place;
        this.isGettingOutOfPenaltyBox = isGettingOutOfPenaltyBox;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public boolean isNotInPenaltyBox() {
        return !isInPenaltyBox();
    }

    private void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    public void moveInPenaltyBox() {
        setInPenaltyBox(true);
    }

    public void moveOutOfPenaltyBox() {
        setInPenaltyBox(false);
    }

    public int getPurse() {
        return purse;
    }

    public void setPurse(int purse) {
        this.purse = purse;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int places) {
        this.place = places;
    }

    public boolean isGettingOutOfPenaltyBox() {
        return isGettingOutOfPenaltyBox;
    }

    public void setGettingOutOfPenaltyBox(boolean gettingOutOfPenaltyBox) {
        isGettingOutOfPenaltyBox = gettingOutOfPenaltyBox;
    }

    public String getName() {
        return name;
    }

    public void moveToNextPlace(int roll) {
        setPlace(getNextPosition(roll));
    }

    private int getNextPosition(int roll) {
        int nextPosition = place + roll;
        if (nextPosition > 11) nextPosition = nextPosition - 12;
        System.out.println(getName() + "'s new location is " + nextPosition);

        return nextPosition;
    }

    @Override
    public String toString() {
        return name;
    }

    public void incrementPurse() {
        purse++;
    }

    public boolean didPlayerWin() {
        return getPurse() >= 6;
    }

}

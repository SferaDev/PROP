package presentation.model;

public class Turn {
    private String guess;
    private int blacks = -1;
    private int whites = -1;

    public Turn(String guess) {
        this.guess = guess;
    }

    public int getBlacks() {
        return blacks;
    }

    public void setBlacks(int blacks) {
        this.blacks = blacks;
    }

    public int getWhites() {
        return whites;
    }

    public void setWhites(int whites) {
        this.whites = whites;
    }

    public String getGuess() {
        return guess;
    }

}

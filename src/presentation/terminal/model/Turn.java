package presentation.terminal.model;

/**
 * The type Turn.
 *
 * @author Elena Alonso Gonzalez
 */
public class Turn {
    private final String guess;
    private int blacks = -1;
    private int whites = -1;

    /**
     * Instantiates a new Turn.
     *
     * @param guess the guess
     */
    public Turn(String guess) {
        this.guess = guess;
    }

    /**
     * Gets blacks.
     *
     * @return the blacks
     */
    public int getBlacks() {
        return blacks;
    }

    /**
     * Sets blacks.
     *
     * @param blacks the blacks
     */
    public void setBlacks(int blacks) {
        this.blacks = blacks;
    }

    /**
     * Gets whites.
     *
     * @return the whites
     */
    public int getWhites() {
        return whites;
    }

    /**
     * Sets whites.
     *
     * @param whites the whites
     */
    public void setWhites(int whites) {
        this.whites = whites;
    }

    /**
     * Gets guess.
     *
     * @return the guess
     */
    public String getGuess() {
        return guess;
    }

}

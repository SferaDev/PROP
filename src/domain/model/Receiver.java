package domain.model;

import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;

/**
 * The interface input and output
 *
 * @author Alexis Rico Carreto
 */
public interface Receiver {
    /**
     * Input the number of blacks
     *
     * @return the number of blacks
     * @throws FinishGameException       the finish game exception
     * @throws CommandInterruptException the command interrupt exception
     */
    int inputControlBlacks() throws FinishGameException, CommandInterruptException;

    /**
     * Input the number of whites
     *
     * @return the number of whites
     * @throws FinishGameException       the finish game exception
     * @throws CommandInterruptException the command interrupt exception
     */
    int inputControlWhites() throws FinishGameException, CommandInterruptException;

    /**
     * Input a combination of colors
     *
     * @param pegs   is the number of pegs in the combination
     * @param colors is the number of different possible colors in a combination
     * @return the combination
     * @throws FinishGameException       the finish game exception
     * @throws CommandInterruptException the command interrupt exception
     */
    int[] inputColorRow(int pegs, int colors) throws FinishGameException, CommandInterruptException;

    /**
     * Input the correct combination of colors
     *
     * @param row is the correct row
     */
    void notifyCorrectRow(String row);

    /**
     * Output the ControlRow
     *
     * @param blacks the number of blacks in the combination
     * @param whites the number of whites int he combination
     */
    void outputControlRow(int blacks, int whites);

    /**
     * Output the ColorRow
     *
     * @param row the row
     */
    void outputColorRow(String row);

    /**
     * Output hint ControlRow to the Maker
     *
     * @param blacks the number of blacks
     * @param whites the number of whites
     */
    void outputHintControlRow(int blacks, int whites);

    /**
     * Output hint ColorRow to the Breaker
     *
     * @param row the combination to help de Breaker
     */
    void outputHintColorRow(String row);

    /**
     * Output message
     *
     * @param message the message
     */
    void outputMessage(String message);

    /**
     * Notify invalid input
     */
    void notifyInvalidInput();

    /**
     * Notify invalid control.
     */
    void notifyInvalidControl();

    /**
     * Start game with a certain title
     *
     * @param title the title
     */
    void startGame(String title);

    /**
     * Finish game and notify score.
     *
     * @param score the score obtained in the game
     */
    void finishGame(int score);

    /**
     * Finish game.
     */
    void finishGame();
}

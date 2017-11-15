package domain;

import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;

/**
 * The interface Input output.
 */
public interface InputOutput {
    /**
     * Input control blacks int.
     *
     * @return the int
     */
    int inputControlBlacks() throws FinishGameException, CommandInterruptException;

    /**
     * Input control whites int.
     *
     * @return the int
     */
    int inputControlWhites() throws FinishGameException, CommandInterruptException;

    /**
     * Input color row int [ ].
     *
     * @param pegs   the pegs
     * @param colors the colors
     * @return the int [ ]
     */
    int[] inputColorRow(int pegs, int colors) throws FinishGameException, CommandInterruptException;

    /**
     * Output control row.
     *
     * @param blacks the blacks
     * @param whites the whites
     */
    void outputControlRow(int blacks, int whites);

    /**
     * Output color row.
     *
     * @param row the row
     */
    void outputColorRow(String row);

    /**
     * Output hint control row.
     *
     * @param blacks the blacks
     * @param whites the whites
     */
    void outputHintControlRow(int blacks, int whites);

    /**
     * Output hint color row.
     *
     * @param row the row
     */
    void outputHintColorRow(String row);

    /**
     * Output message.
     *
     * @param message the message
     */
    void outputMessage(String message);

    /**
     * Notify invalid input.
     */
    void notifyInvalidInput();

    /**
     * Notify score.
     *
     * @param score the score
     */
    void notifyScore(int score);

    void notifyInvalidControl();
}

package domain;

import domain.model.exceptions.FinishGameException;

/**
 * The interface Input output.
 */
public interface InputOutput {
    /**
     * Input control blacks int.
     *
     * @param pegs the pegs
     * @return the int
     */
    int inputControlBlacks(int pegs) throws FinishGameException, InterruptedException;

    /**
     * Input control whites int.
     *
     * @param pegs the pegs
     * @return the int
     */
    int inputControlWhites(int pegs) throws FinishGameException, InterruptedException;

    /**
     * Input color row int [ ].
     *
     * @param pegs   the pegs
     * @param colors the colors
     * @return the int [ ]
     */
    int[] inputColorRow(int pegs, int colors) throws FinishGameException, InterruptedException;

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

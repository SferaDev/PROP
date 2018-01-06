package domain.model.player;

import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;

/**
 * The type Player.
 *
 * @author Alexis Rico Carreto
 */
public abstract class Player implements java.io.Serializable {
    // Role is defined upon creation, only has a getter
    // Protected so it can be accessible on the sub-classes
    private final Role playerRole;

    /**
     * Instantiates a new Player
     *
     * @param role the role of the player
     */
    Player(Role role) {
        playerRole = role;
    }

    /**
     * Opposites the role to know the role of the computer
     *
     * @param role the role
     * @return the opposite role
     */
    public static Role oppositeRole(Role role) {
        if (role == Role.Breaker) return Role.Maker;
        else return Role.Breaker;
    }

    /**
     * Gets player role
     *
     * @return the player role
     */
    public Role getPlayerRole() {
        return playerRole;
    }

    /**
     * Gets name
     *
     * @return the name
     */
    public abstract String getName();

    /**
     * Receive the maker's guess
     *
     * @param pegs   is the number of pegs in the combination
     * @param colors is the number of different possible colors in a combination
     * @return the combination of the correct guess
     * @throws FinishGameException       the finish game exception
     * @throws CommandInterruptException the command interrupt exception
     */
    public abstract ColorRow makerGuess(int pegs, int colors) throws FinishGameException, CommandInterruptException;

    /**
     * Receive the breaker's guess
     *
     * @param pegs   is the number of pegs in the combination
     * @param colors is the number of different possible colors in a combination
     * @return the combination tried by the Breaker
     * @throws FinishGameException       the finish game exception
     * @throws CommandInterruptException the command interrupt exception
     */
    public abstract ColorRow breakerGuess(int pegs, int colors) throws FinishGameException, CommandInterruptException;

    /**
     * Evaluates the correct control row to the breaker's combination
     *
     * @param guess is the combination introduced by the Breaker
     * @return the control row
     * @throws FinishGameException       the finish game exception
     * @throws CommandInterruptException the command interrupt exception
     */
    public abstract ControlRow scoreGuess(ColorRow guess) throws FinishGameException, CommandInterruptException;

    /**
     * Receive the guess row
     *
     * @param guess the control row
     */
    public abstract void receiveColor(ColorRow guess);

    /**
     * Receive the control row
     *
     * @param control the control row
     */
    public abstract void receiveControl(ControlRow control);

    /**
     * Notify invalid input
     */
    public void notifyInvalidInput() {
        // Should never happen unless UserPlayer
    }

    /**
     * Notify invalid control
     */
    public void notifyInvalidControl() {
        // Should never happen unless UserPlayer
    }

    /**
     * Notify hint to the breaker to try a combination
     *
     * @param row the row
     */
    public void notifyHint(ControlRow row) {
        // Should never happen unless UserPlayer
    }

    /**
     * Notify hint to the maker to put the control
     *
     * @param row the row
     */
    public void notifyHint(ColorRow row) {
        // Should never happen unless UserPlayer
    }

    /**
     * Finish Game and notify score
     *
     * @param score the score of the game
     */
    public void finishGame(int score) {
        // Should never happen unless UserPlayer
    }

    /**
     * Finish Game
     */
    public void finishGame() {
        // Should never happen unless UserPlayer
    }

    /**
     * Start Game with Title
     *
     * @param gameTitle the title of the game
     */
    public void startGame(String gameTitle) {
        // Should never happen unless UserPlayer
    }

    /**
     * Notifies the correct guess of the game to the maker
     * @param correctGuess the correct row
     */
    public void notifyCorrectGuess(ColorRow correctGuess) {
        // Should never happen unless UserPlayer
    }

    /**
     * The enum Role.
     */
    public enum Role {
        /**
         * Breaker role.
         */
        Breaker,
        /**
         * Maker role.
         */
        Maker
    }
}

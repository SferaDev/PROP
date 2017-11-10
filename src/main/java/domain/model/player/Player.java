package domain.model.player;

import domain.model.row.ColorRow;
import domain.model.row.ControlRow;

/**
 * The type Player.
 */
public abstract class Player {
    // Role is defined upon creation, only has a getter
    // Protected so it can be accessible on the sub-classes
    private Role playerRole;

    /**
     * Instantiates a new Player.
     *
     * @param role the role
     */
    Player(Role role) {
        playerRole = role;
    }

    /**
     * Opposite role role.
     *
     * @param role the role
     * @return the role
     */
    public static Role oppositeRole(Role role) {
        if (role == Role.BREAKER) return Role.MAKER;
        else return Role.BREAKER;
    }

    /**
     * Gets player role.
     *
     * @return the player role
     */
    public Role getPlayerRole() {
        return playerRole;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public abstract String getName();

    /**
     * Maker guess color row.
     *
     * @param pegs   the pegs
     * @param colors the colors
     * @return the color row
     */
    public abstract ColorRow makerGuess(int pegs, int colors);

    /**
     * Breaker guess color row.
     *
     * @param pegs   the pegs
     * @param colors the colors
     * @return the color row
     */
    public abstract ColorRow breakerGuess(int pegs, int colors);

    /**
     * Score guess control row.
     *
     * @param guess the guess
     * @return the control row
     */
    public abstract ControlRow scoreGuess(ColorRow guess);

    /**
     * Receive control.
     *
     * @param control the control
     */
    public abstract void receiveControl(ControlRow control);

    /**
     * Notify invalid input.
     */
    public void notifyInvalidInput() {
        // Should never happen unless UserPlayer
    }

    /**
     * Notify score.
     *
     * @param score the score
     */
    public void notifyScore(int score) {
    }

    /**
     * The enum Role.
     */
    public enum Role {
        /**
         * Breaker role.
         */
        BREAKER, /**
         * Maker role.
         */
        MAKER;
    }
}
